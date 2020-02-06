package es.upm.miw.rest_controllers.exceptions;

public class BadRequestException extends RuntimeException {

    private static final String DESCRIPTION = "Bad Request Exception (400)";

    public BadRequestException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}
