package es.upm.miw.rest_controllers.exceptions;

public class PdfException extends RuntimeException {

    private static final String DESCRIPTION = "Pdf. File exception";

    public PdfException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}
