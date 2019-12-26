package es.upm.miw.restControllers.exceptions;

public class MailException extends ConflictException {

    private static final String DESCRIPTION = "Token with wrong format";

    public MailException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}
