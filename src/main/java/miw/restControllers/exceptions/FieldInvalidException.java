package miw.restControllers.exceptions;

public class FieldInvalidException extends Exception {

    public static final String DESCRIPTION = "Invalid Field";
    private static final long serialVersionUID = -1344640670884805385L;

    public FieldInvalidException() {
        this("");
    }

    public FieldInvalidException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}
