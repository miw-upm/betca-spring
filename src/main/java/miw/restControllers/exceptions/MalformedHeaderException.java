package miw.restControllers.exceptions;

public class MalformedHeaderException extends Exception {

    public static final String DESCRIPTION = "Cabecera por formato erroneo";
    private static final long serialVersionUID = -1344640670884805385L;

    public MalformedHeaderException() {
        this("");
    }

    public MalformedHeaderException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}
