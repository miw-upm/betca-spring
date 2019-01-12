package miw.restControllers;

public class MalformedHeaderException extends Exception {

    public static final String DESCRIPTION = "Error de Authorization en cabecera por formato erroneo, debe estar en Auth Basic";
    private static final long serialVersionUID = -1344640670884805385L;

    public MalformedHeaderException() {
        this("");
    }

    public MalformedHeaderException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}
