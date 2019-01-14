package miw.restControllers.exceptions;

public class UnauthorizedException extends Exception {

    public static final String DESCRIPTION = "La identificación utilizada no tiene la autoridad suficiente";
    private static final long serialVersionUID = -1344640670884805385L;

    public UnauthorizedException() {
        this("");
    }

    public UnauthorizedException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}
