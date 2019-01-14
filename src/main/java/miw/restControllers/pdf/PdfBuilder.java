package miw.restControllers.pdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.DottedLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.LineSeparator;
import miw.restControllers.exceptions.PdfException;
import org.apache.logging.log4j.LogManager;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.layout.element.Paragraph;

public class PdfBuilder {

    public static final String USER_HOME = "user.home";
    public static final String ROOT_PDFS = "/spring/pdfs/file";
    public static final String PDF_FILE_EXT = ".pdf";
    private static final int LINE_GAP = 2;
    private static final float LINE_WIDTH = 0.5f;

    private String filename;

    private Document document;

    public PdfBuilder() throws PdfException {
        this.filename = System.getProperty(USER_HOME) + ROOT_PDFS + PDF_FILE_EXT;
        this.prepareDocument(PageSize.A4);
    }

    private void prepareDocument(PageSize pageSize) throws PdfException {
        File file = new File(this.filename);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            PdfDocument pdf = new PdfDocument(new PdfWriter(filename));
            document = new Document(pdf, pageSize);
        } catch (FileNotFoundException fnfe) {
            LogManager.getLogger(this.getClass())
                    .error("PdfBuilder::prepareDocuemnt. Error when creating the pdf document (" + this.filename + "). " + fnfe);
            throw new PdfException("Can’t create PDF (" + this.filename + ")");
        }
    }

    public PdfBuilder paragraphEmphasized(String text) {
        this.document.add(new Paragraph(text).setBold().setFontSize(10));
        return this;
    }

    public PdfBuilder paragraph(String text) {
        this.document.add(new Paragraph(text));
        return this;
    }

    public PdfBuilder line() {
        DottedLine separator = new DottedLine();
        separator.setGap(LINE_GAP);
        separator.setLineWidth(LINE_WIDTH);
        document.add(new LineSeparator(separator));
        return this;
    }

    public byte[] build() throws PdfException {
        this.document.close();
        try {
            return Files.readAllBytes(new File(this.filename).toPath());
        } catch (IOException ioe) {
            LogManager.getLogger(this.getClass()).error("PdfTicketBuilder::build. Error when read bytes to PDF. " + ioe);
            throw new PdfException("Can’t read PDF (" + this.filename + ")");
        }
    }

}
