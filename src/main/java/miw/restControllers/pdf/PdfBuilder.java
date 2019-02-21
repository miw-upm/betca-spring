package miw.restControllers.pdf;

import com.itextpdf.barcodes.Barcode128;
import com.itextpdf.barcodes.BarcodeQRCode;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.DottedLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import miw.restControllers.exceptions.PdfException;
import org.apache.logging.log4j.LogManager;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

public class PdfBuilder {

    public static final String USER_HOME = "user.home";
    public static final String ROOT_PDFS = "/spring/pdfs/file";
    public static final String PDF_FILE_EXT = ".pdf";
    private static final int LINE_GAP = 2;
    private static final int FONT_SIZE_EMPHASIZEDD = 10;
    private static final float LINE_WIDTH = 0.5f;
    private static final int BAR_CODE_HEIGHT = 50;
    private static final int QR_CODE_PERCENT = 50;
    private static final int IMAGE_WIDTH = 80;

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
            LogManager.getLogger(this.getClass()).error(
                    "PdfBuilder::prepareDocuemnt. Error when creating the pdf document (" + this.filename + "). "
                            + fnfe);
            throw new PdfException("Can’t create PDF (" + this.filename + ")");
        }
    }

    public PdfBuilder paragraphEmphasized(String text) {
        this.document.add(new Paragraph(text).setBold().setFontSize(FONT_SIZE_EMPHASIZEDD));
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

    public PdfBuilder barCode(String code) {
        Barcode128 code128 = new Barcode128(this.document.getPdfDocument());
        code128.setCodeType(Barcode128.CODE128);
        code128.setCode(code.trim().replace('-', '/').replace('_', '?'));// UTF8
        code128.setAltText(code.trim());
        Image code128Image = new Image(code128.createFormXObject(this.document.getPdfDocument()));
        code128Image.setWidthPercent(code128Image.getImageWidth());
        code128Image.setHeight(BAR_CODE_HEIGHT);
        code128Image.setHorizontalAlignment(HorizontalAlignment.CENTER);
        this.document.add(code128Image);
        return this;
    }

    public PdfBuilder qrCode(String code) {
        BarcodeQRCode qrcode = new BarcodeQRCode(code.trim());
        Image qrcodeImage = new Image(qrcode.createFormXObject(this.document.getPdfDocument()));
        qrcodeImage.setHorizontalAlignment(HorizontalAlignment.CENTER);
        qrcodeImage.setWidthPercent(QR_CODE_PERCENT);
        this.document.add(qrcodeImage);
        Paragraph paragraph = new Paragraph("Ref. " + code);
        paragraph.setTextAlignment(TextAlignment.CENTER);
        this.document.add(paragraph);
        return this;
    }

    public PdfBuilder image(String fileName) throws PdfException {
        try {
            Image img = new Image(ImageDataFactory.create(new ClassPathResource("img/" + fileName).getURL()));
            img.setWidth(IMAGE_WIDTH);
            img.setHorizontalAlignment(HorizontalAlignment.CENTER);
            this.document.add(img);
        } catch (IOException e) {
            LogManager.getLogger(this.getClass()).error("PdfTicketBuilder::addImage. Error when add image to PDF (" + fileName + "). " + e);
            throw new PdfException("Can’t add image to PDF (" + fileName + ")");
        }
        return this;
    }

    public PdfTableBuilder table(float... widths) {
        return new PdfTableBuilder(this, this.document, widths);
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
