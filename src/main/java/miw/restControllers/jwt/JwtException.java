package miw.restControllers.jwt;

import java.io.IOException;

public class JwtException extends IOException {

    public static final String DESCRIPTION = "Jwt exception";
    private static final long serialVersionUID = -1344640670884805385L;

    public JwtException() {
        this("");
    }

    public JwtException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}
