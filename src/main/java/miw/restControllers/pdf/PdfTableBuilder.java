package miw.restControllers.pdf;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

public class PdfTableBuilder {

    private static final int FONT_SIZE_EMPHASIZEDD = 10;

    private PdfBuilder pdfBuilder;

    private Document document;

    private Table table;


    PdfTableBuilder(PdfBuilder pdfbuilder, Document document, float... widths) {
        this.pdfBuilder = pdfbuilder;
        this.document = document;
        this.table = new Table(widths, true);
        this.table.setBorder(new SolidBorder(Color.WHITE, 2));
        this.table.setVerticalAlignment(VerticalAlignment.MIDDLE);
        this.table.setHorizontalAlignment(HorizontalAlignment.CENTER);
        this.table.setTextAlignment(TextAlignment.RIGHT);
    }

    public PdfTableBuilder tableColumnsHeader(String... headers) {
        for (String header : headers) {
            this.table.addHeaderCell(header);
        }
        return this;
    }

    public PdfTableBuilder tableCell(String... cells) {
        for (String cell : cells) {
            this.table.addCell(cell);
        }
        return this;
    }

    public PdfTableBuilder tableColspanRight(String value) {
        Cell cell = new Cell(1, this.table.getNumberOfColumns());
        cell.setTextAlignment(TextAlignment.RIGHT).setBold().setFontSize(FONT_SIZE_EMPHASIZEDD);
        cell.add(value);
        this.table.addCell(cell);
        return this;
    }

    public PdfBuilder closeTable() {
        this.document.add(this.table);
        return this.pdfBuilder;
    }

}
