package utils.pdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import technology.tabula.ObjectExtractor;
import technology.tabula.Page;
import technology.tabula.PageIterator;
import technology.tabula.Pair;
import technology.tabula.Rectangle;
import technology.tabula.RectangularTextContainer;
import technology.tabula.Table;
import technology.tabula.detectors.DetectionAlgorithm;
import technology.tabula.detectors.NurminenDetectionAlgorithm;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;

public class PdfTableExtractUtils {

    /**
     * Retrieving the page number where the table is located using the table section title
     * @param pdfFile PDF File as stream
     * @param tableTitle Table section title
     * @return Number of the page where the table is located
     */
    public static int getTablePageNumberFromTableTitle(File pdfFile, String tableTitle) throws IOException {

        FileInputStream fis = new FileInputStream(pdfFile);

        try {
            return getTablePageNumberFromTableTitle(fis, tableTitle);
        }
        finally {
            fis.close();
        }
    }

    /**
     * Retrieving the page number where the table is located using the table section title
     * @param inputStream PDF File as stream
     * @param tableTitle Table section title
     * @return Number of the page where the table is located
     */
    public static int getTablePageNumberFromTableTitle(InputStream inputStream, String tableTitle) throws IOException {

        PDDocument document = PDDocument.load(inputStream);
        int pageNumber = -1;

        try {

            for( int i = 1; i <= document.getNumberOfPages(); i++ ) {

                PDFTextStripper textStripper = new PDFTextStripper();
                textStripper.setStartPage(i);
                textStripper.setEndPage(i);
                String pageText = textStripper.getText(document);

                if(StringUtils.containsIgnoreCase(pageText,tableTitle)) {
                    pageNumber = i;
                    break;
                }
            }
        }
        finally {
            document.close();
        }

        return pageNumber;
    }

    /**
     * Retrieving the page number where the table is located using the table header (search horizontally then vertically)
     * @param pdfFile PDF File
     * @param tableHeader Table header to search (each column separated by |. Partial search possible). Example : Event Time|Event Description|Old Value|New Value
     * @return Number of the page where the table is located
     */
    public static int getTablePageNumberFromTableHeader(File pdfFile, String tableHeader) throws IOException {

        FileInputStream fis = new FileInputStream(pdfFile);

        try {
            return getTablePageNumberFromTableHeader(fis, tableHeader);
        }
        finally {
            fis.close();
        }
    }

    /**
     * Retrieving the page number where the table is located using the table header (search horizontally then vertically)
     * @param inputStream PDF File as stream
     * @param tableHeader Table header to search (each column separated by |. Partial search possible). Example : Event Time|Event Description|Old Value|New Value
     * @return Number of the page where the table is located
     */
    public static int getTablePageNumberFromTableHeader(InputStream inputStream, String tableHeader) throws IOException {

        PDDocument document = PDDocument.load(inputStream);
        int pageNumber = -1;

        try {
            ObjectExtractor oe = new ObjectExtractor(document);
            SpreadsheetExtractionAlgorithm sea = new SpreadsheetExtractionAlgorithm();
            PageIterator pageIterator = oe.extract();

            // foreach each page of the pdf
            while(pageIterator.hasNext()) {

                Page page = pageIterator.next();

                // extract all tables of the page and check if the searched table is present
                List<Table> tableList = sea.extract(page);
                Table table = searchTable(tableList, tableHeader);

                if(table != null) {
                    pageNumber = page.getPageNumber();
                    break;
                }
            }
        }
        finally {
            document.close();
        }

        return pageNumber;
    }

    /**
     * Retrieving table using the table header (search horizontally then vertically)
     * @param pdfFile PDF File
     * @param tableHeader Table header to search (each column separated by |. Partial search possible). Example : Event Time|Event Description|Old Value|New Value
     * @return Table
     */
    public static Table getTableFromTableHeader(File pdfFile, String tableHeader) throws IOException {

        FileInputStream fis = new FileInputStream(pdfFile);

        try {
            return getTableFromTableHeader(fis, tableHeader);
        }
        finally {
            fis.close();
        }
    }

    /**
     * Retrieving table using the table header (search horizontally then vertically)
     * @param inputStream PDF File as stream
     * @param tableHeader Table header to search (each column separated by |. Partial search possible). Example : Event Time|Event Description|Old Value|New Value
     * @return Table
     */
    public static Table getTableFromTableHeader(InputStream inputStream, String tableHeader) throws IOException {

        PDDocument document = PDDocument.load(inputStream);
        Table foundTable = null;

        try {
            ObjectExtractor oe = new ObjectExtractor(document);
            SpreadsheetExtractionAlgorithm sea = new SpreadsheetExtractionAlgorithm();
            PageIterator pageIterator = oe.extract();

            while(pageIterator.hasNext()) {

                Page page = pageIterator.next();

                // extract all tables of the page and check if the searched table is present
                List<Table> tableList = sea.extract(page);
                Table table = searchTable(tableList, tableHeader);

                if(table != null) {
                    //foundTable = table; // without auto detection table
                    foundTable = getTablesFromPage(page).stream().findFirst().get();
                    break;
                }
            }
        }
        finally {
            document.close();
        }

        return foundTable;
    }

    /**
     * Get tables from page number (with auto table detection)
     * @param pdfFile PDF File
     * @param pageNumber Page number
     * @return Tables
     */
    public static List<Table> getTablesFromPageNumber(File pdfFile, int pageNumber) throws IOException {

        FileInputStream fis = new FileInputStream(pdfFile);

        try {
            return getTablesFromPageNumber(fis, pageNumber);
        }
        finally {
            fis.close();
        }
    }

    /**
     * Get tables from page number (with auto table detection)
     * @param inputStream PDF File as Stream
     * @param pageNumber Page number
     * @return Tables
     */
    public static List<Table> getTablesFromPageNumber(InputStream inputStream, int pageNumber) throws IOException {

        List<Table> tableList = new ArrayList<>();
        TableExtractor tableExtractor = new TableExtractor();
        tableExtractor.setGuess(true);
        tableExtractor.setMethod(ExtractionMethod.SPREADSHEET);

        // load document
        PDDocument document = PDDocument.load(inputStream);

        try {
            ObjectExtractor oe = new ObjectExtractor(document);
            Page page = oe.extract(pageNumber);

            if(page != null) {

                tableList.addAll(getTablesFromPage(page));
            }
        }
        finally {
            document.close();
        }

        return tableList;
    }

    /**
     * Get tables from page (with auto table detection)
     * @param page Page
     * @return Tables
     */
    public static List<Table> getTablesFromPage(Page page) throws IOException {

        List<Table> tableList = new ArrayList<>();
        TableExtractor tableExtractor = new TableExtractor();
        tableExtractor.setGuess(true);
        tableExtractor.setMethod(ExtractionMethod.SPREADSHEET);

        if(page != null) {

            // guess the page areas to extract using a detection algorithm
            DetectionAlgorithm detector = new NurminenDetectionAlgorithm();
            List<Rectangle> guesses = detector.detect(page);

            for (Rectangle guessRect : guesses) {

                Pair<Integer, Rectangle> areaPair = new Pair<Integer, Rectangle>(1,
                    new Rectangle(guessRect.getTop(), guessRect.getLeft(),
                        guessRect.getRight() - guessRect.getLeft(),
                        guessRect.getBottom() - guessRect.getTop()));
                Rectangle area = areaPair.getRight();
                tableList.addAll(tableExtractor.extractTables(page.getArea(area)));
            }
        }

        return tableList;
    }

    private static Table searchTable(List<Table> tableList, String tableHeader) {

        for (Table table : tableList) {

            Table searchTable = searchTableWithHeaderHorizontally(table, tableHeader);

            if(searchTable != null) {
                return searchTable;
            }
            else {

                searchTable = searchTableWithHeaderVertically(table, tableHeader);

                if(searchTable != null) {
                    return searchTable;
                }
            }
        }

        return null;
    }

    private static Table searchTableWithHeaderHorizontally(Table table, String tableHeader) {

        Table correspondingTable = null;
        List<List<RectangularTextContainer>> rows = table.getRows();

        for (int i = 0; i < rows.size(); i++) {

            StringBuilder sb = new StringBuilder();
            List<RectangularTextContainer> cells = rows.get(i);

            for (int j = 0; j < cells.size(); j++) {
                sb.append(cells.get(j).getText());
            }

            if(StringUtils.containsIgnoreCase(sb.toString(), tableHeader.replace("|", ""))) {
                correspondingTable = table;
                break;
            }
        }

        return correspondingTable;
    }

    private static Table searchTableWithHeaderVertically(Table table, String tableHeader) {

        Table correspondingTable = null;
        List<List<RectangularTextContainer>> rows = table.getRows();

        for(int i = 0; i < table.getColCount(); i++) {

            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < rows.size(); j++) {

                sb.append(rows.get(j).get(i).getText());
            }

            if(StringUtils.containsIgnoreCase(sb.toString(), tableHeader.replace("|", ""))) {
                correspondingTable = table;
                break;
            }
        }

        return correspondingTable;
    }

    /**
     * Print table
     * @param table Table to print
     */
    public static void printTable(Table table) {

        if(table != null) {

            List<List<RectangularTextContainer>> rows = table.getRows();

            for (int i = 0; i < rows.size(); i++) {

                StringBuilder sb = new StringBuilder();
                List<RectangularTextContainer> cells = rows.get(i);

                sb.append("Row " + i + " | ");
                for (int j = 0; j < cells.size(); j++) {
                    sb.append(cells.get(j).getText() + "|");
                }

                System.out.println(sb.toString());
            }
        }
    }

    /**
     * Print tables
     * @param tables List of tables
     */
    public static void printTables(List<Table> tables) {

        for(Table table : tables) {
            System.out.println();
            printTable(table);
        }
    }

    /**
     * Get table field value from field name
     * @param table Table
     * @param fieldName field name to search
     * @return Field value
     */
    public static String getTableFieldValue(Table table, String fieldName) {

        boolean nextCellContainsValue = false;
        List<List<RectangularTextContainer>> rows = table.getRows();

        for (int i = 0; i < rows.size(); i++) {

            StringBuilder sb = new StringBuilder();
            List<RectangularTextContainer> cells = rows.get(i);

            for (int j = 0; j < cells.size(); j++) {
                String cellValue = cells.get(j).getText();

                if(nextCellContainsValue) {
                    if(!cellValue.trim().isEmpty()) {
                        return cellValue;
                    }
                }
                else {

                    if (StringUtils.containsIgnoreCase(StringUtils.trim(cellValue.trim()), StringUtils.trim(fieldName))) {
                        nextCellContainsValue = true;
                    }
                }
            }
        }

        return null;
    }
}