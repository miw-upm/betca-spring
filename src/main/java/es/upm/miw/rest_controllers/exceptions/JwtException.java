package es.upm.miw.rest_controllers.exceptions;

public class JwtException extends UnauthorizedException {

    private static final String DESCRIPTION = "Jwt exception";

    public JwtException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}
