package es.upm.miw.betca_rest.http_errors;

public class BadRequestException extends RuntimeException {
    private static final String DESCRIPTION = "Bad Request Exception";

    public BadRequestException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}
