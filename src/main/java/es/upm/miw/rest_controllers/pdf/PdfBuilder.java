package es.upm.miw.rest_controllers.pdf;

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
import es.upm.miw.rest_controllers.exceptions.PdfException;
import org.apache.logging.log4j.LogManager;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

public class PdfBuilder {

    public static final String USER_HOME = "user.home";
    public static final String PDF_FILE_EXT = ".pdf";

    private static final int LINE_GAP = 2;
    private static final float LINE_WIDTH = 0.5f;
    private static final int BAR_CODE_HEIGHT = 50;
    private static final int QR_CODE_PERCENT = 50;
    private static final int IMAGE_WIDTH = 80;

    private static final int THERMAL_FONT_SIZE = 7;
    private static final int THERMAL_FONT_SIZE_EMPHASIZED = 10;
    private static final int THERMAL_MARGIN_LEFT = 10;
    private static final int THERMAL_MARGIN_RIGHT = 14;
    private static final int THERMAL_MARGIN_TOP_BOTTOM = 12;
    private static final float THERMAL_PAGE_WIDTH = 227;
    private static final float THERMAL_PAGE_HEIGHT = 800;

    private String filename;

    private Document document;

    public PdfBuilder(String path) {
        this.filename = System.getProperty(USER_HOME) + path + PDF_FILE_EXT;
        this.prepareDocument(new PageSize(THERMAL_PAGE_WIDTH, THERMAL_PAGE_HEIGHT));
        this.document.setMargins(THERMAL_MARGIN_TOP_BOTTOM, THERMAL_MARGIN_RIGHT, THERMAL_MARGIN_TOP_BOTTOM, THERMAL_MARGIN_LEFT);
        this.document.setFontSize(THERMAL_FONT_SIZE);
    }

    private void prepareDocument(PageSize pageSize) {
        File file = new File(this.filename);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
            } catch (SecurityException s) {
                LogManager.getLogger(this.getClass()).error(
                        String.format("PdfBuilder::prepareDocument. Error when creating the pdf document (%s). %s", this.filename, s));
                throw new PdfException("Can’t create PDF (" + this.filename + ")");
            }
        }
        try {
            this.document = new Document(new PdfDocument(new PdfWriter(filename)), pageSize);
        } catch (FileNotFoundException fnfe) {
            LogManager.getLogger(this.getClass()).error(
                    String.format("PdfBuilder::prepareDocument. Error when creating the pdf document (%s). %s", this.filename, fnfe));
            throw new PdfException("Can’t create PDF (" + this.filename + ")");
        }
    }

    public PdfBuilder paragraphEmphasized(String text) {
        this.document.add(new Paragraph(text).setBold().setFontSize(THERMAL_FONT_SIZE_EMPHASIZED));
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
        BarcodeQRCode barcodeQRCode = new BarcodeQRCode(code.trim());
        Image qcCodeImage = new Image(barcodeQRCode.createFormXObject(this.document.getPdfDocument()));
        qcCodeImage.setHorizontalAlignment(HorizontalAlignment.CENTER);
        qcCodeImage.setWidthPercent(QR_CODE_PERCENT);
        this.document.add(qcCodeImage);
        Paragraph paragraph = new Paragraph("Ref. " + code);
        paragraph.setTextAlignment(TextAlignment.CENTER);
        this.document.add(paragraph);
        return this;
    }

    public PdfBuilder image(String fileName) {
        try {
            Image img = new Image(ImageDataFactory.create(new ClassPathResource("img/" + fileName).getURL()));
            img.setWidth(IMAGE_WIDTH);
            img.setHorizontalAlignment(HorizontalAlignment.CENTER);
            this.document.add(img);
        } catch (IOException e) {
            LogManager.getLogger(this.getClass()).error(String.format("PdfTicketBuilder::addImage. Error when add image to PDF (%s). %s", fileName, e));
            throw new PdfException("Can’t add image to PDF (" + fileName + ")");
        }
        return this;
    }

    public PdfTableBuilder table(float... widths) {
        return new PdfTableBuilder(this, this.document, widths);
    }

    public byte[] build() {
        this.document.close();
        try {
            return Files.readAllBytes(new File(this.filename).toPath());
        } catch (IOException ioe) {
            LogManager.getLogger(this.getClass()).error(String.format("PdfTicketBuilder::build. Error when read bytes to PDF. %s", ioe));
            throw new PdfException("Can’t read PDF (" + this.filename + ")");
        }
    }
}
