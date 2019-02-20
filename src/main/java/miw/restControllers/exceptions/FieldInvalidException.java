package miw.restControllers.exceptions;

public class FieldInvalidException extends BadRequestException {

    private static final String DESCRIPTION = "Invalid Field";

    public FieldInvalidException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}
