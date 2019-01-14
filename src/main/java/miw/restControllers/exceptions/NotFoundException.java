package miw.restControllers.exceptions;

public class NotFoundException extends Exception {

    public static final String DESCRIPTION = "Not Found Exception";
    private static final long serialVersionUID = -1344640670884805385L;

    public NotFoundException() {
        this("");
    }

    public NotFoundException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}
