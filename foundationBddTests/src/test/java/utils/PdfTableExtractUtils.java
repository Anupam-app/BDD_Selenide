package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import technology.tabula.ObjectExtractor;
import technology.tabula.Page;
import technology.tabula.PageIterator;
import technology.tabula.Pair;
import technology.tabula.Rectangle;
import technology.tabula.RectangularTextContainer;
import technology.tabula.detectors.DetectionAlgorithm;
import technology.tabula.detectors.NurminenDetectionAlgorithm;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;
import utils.pdf.PdfTable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PdfTableExtractUtils {

    /**
     * Get page number (first found) where table is located using table section title
     * 
     * @param inputStream PDF File as stream
     * @param tableTitle Table section title
     * @return Number of the page where the table is located
     */
    public static int getTablePageNumberFromTableTitle(InputStream inputStream, String tableTitle) throws IOException {

        int pageNumber = -1;

        try (PDDocument document = PDDocument.load(inputStream)) {

            for (int i = 1; i <= document.getNumberOfPages(); i++) {

                PDFTextStripper textStripper = new PDFTextStripper();
                textStripper.setStartPage(i);
                textStripper.setEndPage(i);
                String pageText = textStripper.getText(document);

                if (StringUtils.containsIgnoreCase(pageText, tableTitle)) {
                    pageNumber = i;
                    break;
                }
            }
        }

        return pageNumber;
    }

    /**
     * Get tables from table section title
     * 
     * @param inputStream PDF File as stream
     * @param tableTitle Table section title
     * @return Table
     */
    public static List<PdfTable> getTablesFromTableTitle(InputStream inputStream, String tableTitle) throws IOException {

        List<PdfTable> resultTable = new ArrayList<>();

        try (PDDocument document = PDDocument.load(inputStream)) {

            ObjectExtractor oe = new ObjectExtractor(document);

            for (int i = 0; i <= document.getNumberOfPages(); i++) {

                PDFTextStripper textStripper = new PDFTextStripper();
                textStripper.setStartPage(i);
                textStripper.setEndPage(i);
                String pageText = textStripper.getText(document);

                if (StringUtils.containsIgnoreCase(pageText, tableTitle)) {
                    Page page = oe.extract(i);

                    if (page != null) {
                        resultTable.addAll(new ArrayList<>(getTablesFromPage(page)));
                    }
                }
            }
        }

        return resultTable;
    }
    public static PdfTable getTableFromTableTitles(InputStream inputStream, String tableTitle) throws IOException {

        PdfTable resultTable = null;

        try (PDDocument document = PDDocument.load(inputStream)) {

            ObjectExtractor oe = new ObjectExtractor(document);

            for (int i = 1; i <= document.getNumberOfPages(); i++) {

                PDFTextStripper textStripper = new PDFTextStripper();
                textStripper.setStartPage(i);
                textStripper.setEndPage(i);
                String pageText = textStripper.getText(document);

                if (StringUtils.containsIgnoreCase(pageText, tableTitle)) {
                    Page page = oe.extract(i);

                    if (page != null) {
                        resultTable = getTablesFromPage(page).stream().findFirst().orElse(null);
                    }

                    break;
                }
            }
        }

        return resultTable;
    }

    /**
     * Get the page number (first found) where table is located using table header (search horizontally then vertically)
     * 
     * @param inputStream PDF File as stream
     * @param tableHeader Table header to search (each column separated by |. Partial search possible). Example : Event
     *        Time|Event Description|Old Value|New Value
     * @return Number of the page where the table is located
     */
    public static int getTablePageNumberFromTableHeader(InputStream inputStream, String tableHeader)
            throws IOException {

        int pageNumber = -1;

        try (PDDocument document = PDDocument.load(inputStream)) {

            ObjectExtractor oe = new ObjectExtractor(document);
            PageIterator pageIterator = oe.extract();

            while (pageIterator.hasNext()) {

                Page page = pageIterator.next();
                List<PdfTable> tableList = getTablesFromPage(page);
                PdfTable table = searchTable(tableList, tableHeader);

                if (table != null) {
                    pageNumber = page.getPageNumber();
                    break;
                }
            }
        }

        return pageNumber;
    }

    /**
     * Get table (first found) using table header (search horizontally then vertically)
     * 
     * @param inputStream PDF File as stream
     * @param tableHeader Table header to search (each column separated by |. Partial search possible). Example : Event
     *        Time|Event Description|Old Value|New Value
     * @return Table
     */
    public static PdfTable getTableFromTableHeader(InputStream inputStream, String tableHeader) throws IOException {

        PdfTable resultTable = null;

        try (PDDocument document = PDDocument.load(inputStream)) {

            ObjectExtractor oe = new ObjectExtractor(document);
            PageIterator pageIterator = oe.extract();

            while (pageIterator.hasNext()) {

                Page page = pageIterator.next();
                List<PdfTable> tableList = getTablesFromPage(page);
                PdfTable table = searchTable(tableList, tableHeader);

                if (table != null) {
                    resultTable = table;
                    break;
                }
            }
        }

        return resultTable;
    }

    /**
     * Get tables from page number (with auto table detection)
     * 
     * @param inputStream PDF File as Stream
     * @param pageNumber Page number
     * @return Tables
     */
    public static List<PdfTable> getTablesFromPageNumber(InputStream inputStream, int pageNumber) throws IOException {

        List<PdfTable> tableList = new ArrayList<>();

        // load document
        try (PDDocument document = PDDocument.load(inputStream)) {
            ObjectExtractor oe = new ObjectExtractor(document);
            Page page = oe.extract(pageNumber);

            if (page != null) {
                tableList = getTablesFromPage(page);
            }
        }

        return tableList;
    }

    /**
     * Get all tables
     * 
     * @param inputStream PDF File as Stream
     * @return Tables
     */
    public static List<PdfTable> getTables(InputStream inputStream) throws IOException {

        List<PdfTable> tableList = new ArrayList<>();

        // load document
        try (PDDocument document = PDDocument.load(inputStream)) {
            ObjectExtractor oe = new ObjectExtractor(document);
            PageIterator pageIterator = oe.extract();

            // foreach each page of the pdf
            while (pageIterator.hasNext()) {
                Page page = pageIterator.next();
                tableList.addAll(getTablesFromPage(page));
            }
        }

        return tableList;
    }

    /**
     * Get tables from page (with auto table detection)
     * 
     * @param page Page
     * @return Tables
     */
    public static List<PdfTable> getTablesFromPage(Page page) {

        List<PdfTable> tableList = new ArrayList<>();
        SpreadsheetExtractionAlgorithm tableExtractor = new SpreadsheetExtractionAlgorithm();

        if (page != null) {

            // guess the page areas to extract using a detection algorithm
            DetectionAlgorithm detector = new NurminenDetectionAlgorithm();
            List<Rectangle> guesses = detector.detect(page);

            for (Rectangle guessRect : guesses) {

                Pair<Integer, Rectangle> areaPair = new Pair<>(1, new Rectangle(guessRect.getTop(), guessRect.getLeft(),
                        guessRect.getRight() - guessRect.getLeft(), guessRect.getBottom() - guessRect.getTop()));

                tableList.addAll(
                    tableExtractor.extract(page.getArea(areaPair.getRight())).stream().map(t -> new PdfTable(t, page)).collect(
                        Collectors.toList()));
            }
        }

        return tableList;
    }

    public static PdfTable searchTable(List<PdfTable> tableList, String tableHeader) {

        for (PdfTable table : tableList) {

            PdfTable searchTable = searchTableWithHeaderHorizontally(table, tableHeader);

            if (searchTable != null) {
                return searchTable;
            } else {

                searchTable = searchTableWithHeaderVertically(table, tableHeader);

                if (searchTable != null) {
                    return searchTable;
                }
            }
        }

        return null;
    }

    private static PdfTable searchTableWithHeaderHorizontally(PdfTable table, String tableHeader) {

        PdfTable correspondingTable = null;

        for (List<RectangularTextContainer> row : table.getRows()) {

            StringBuilder sb = new StringBuilder();

            for (RectangularTextContainer cell : row) {
                sb.append(cell.getText());
            }

            if (StringUtils.containsIgnoreCase(sb.toString(), tableHeader.replace("|", ""))) {
                correspondingTable = table;
                break;
            }
        }

        return correspondingTable;
    }

    private static PdfTable searchTableWithHeaderVertically(PdfTable table, String tableHeader) {

        PdfTable correspondingTable = null;
        List<List<RectangularTextContainer>> rows = table.getRows();

        for (int i = 0; i < table.getColCount(); i++) {

            StringBuilder sb = new StringBuilder();

            for (List<RectangularTextContainer> row : rows) {

                sb.append(row.get(i).getText());
            }

            if (StringUtils.containsIgnoreCase(sb.toString(), tableHeader.replace("|", ""))) {
                correspondingTable = table;
                break;
            }
        }

        return correspondingTable;
    }

    /**
     * Get table field value from field name
     * 
     * @param table Table
     * @param fieldName field name to search
     * @return Field value
     */
    public static String getTableFieldValue(PdfTable table, String fieldName) {

        boolean nextCellContainsValue = false;

        for (List<RectangularTextContainer> row : table.getRows()) {

            for (RectangularTextContainer cell : row) {
                String cellValue = cell.getText();

                if (nextCellContainsValue) {
                    if (!cellValue.trim().isEmpty()) {
                        return cellValue;
                    }
                } else {

                    if (StringUtils.containsIgnoreCase(StringUtils.trim(cellValue.trim()),
                            StringUtils.trim(fieldName))) {
                        nextCellContainsValue = true;
                    }
                }
            }
        }

        return null;
    }

    /**
     * Get column index of column with specified column label
     * 
     * @param table Table
     * @param columnLabel Column label to search
     * @return Index of column
     */
    public static int getColumnIndex(PdfTable table, String columnLabel) {

        int columnIndex = -1;

        for (int j = 0; j < table.getColCount(); j++) {
            if (StringUtils.equalsIgnoreCase(StringUtils.trim(table.getRows().get(0).get(j).getText()), columnLabel)) {
                columnIndex = j;
                break;
            }
        }

        return columnIndex;
    }

    /**
     * Extract column data
     * @param table Source table
     * @param columnIndex Column index to extract data
     * @return Table with column data
     */
    public static PdfTable extractColumnData(PdfTable table, int columnIndex) {

        SpreadsheetExtractionAlgorithm tableExtractor = new SpreadsheetExtractionAlgorithm();

        // get coordinate of specified column
        Rectangle columnCoordinate = table.getTable().getCell(0, columnIndex);

        // extract only specified column
        Pair<Integer, Rectangle> areaPair = new Pair<>(1, new Rectangle(table.getTable().getTop(), columnCoordinate.getLeft(),
            (float) columnCoordinate.getWidth(), (float) table.getTable().getHeight()));


        return tableExtractor.extract(table.getPage().getArea(areaPair.getRight()))
            .stream().findFirst().map(t -> new PdfTable(t, table.getPage())).orElse(null);
    }
}
