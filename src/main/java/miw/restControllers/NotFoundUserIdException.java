package miw.restControllers;

public class NotFoundUserIdException extends Exception {

    public static final String DESCRIPTION = "No se encuentra el identificador de usuario utilizado";
    private static final long serialVersionUID = -1344640670884805385L;

    public NotFoundUserIdException() {
        this("");
    }

    public NotFoundUserIdException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}
