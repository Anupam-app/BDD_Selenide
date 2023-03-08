package utils.pdf;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import technology.tabula.Page;
import technology.tabula.RectangularTextContainer;
import technology.tabula.Table;

@AllArgsConstructor
@Data
public class PdfTable {

    private Table table;
    private Page page;

    public List<List<RectangularTextContainer>> getRows() {
        return table.getRows();
    }

    public int getColCount() {
        return table.getColCount();
    }

    public int getRowCount() {
        return table.getRowCount();
    }
}
