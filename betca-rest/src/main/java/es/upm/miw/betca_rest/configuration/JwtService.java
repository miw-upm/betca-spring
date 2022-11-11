package es.upm.miw.betca_rest.configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JwtService {
    private static final String BEARER = "Bearer ";
    private static final String USER_CLAIM = "user";
    private static final String NAME_CLAIM = "name";
    private static final String ROLE_CLAIM = "role";

    private String secret;
    private String issuer;
    private int expire;

    @Autowired
    public JwtService(@Value("${miw.jwt.secret}") String secret, @Value("${miw.jwt.issuer}") String issuer,
                      @Value("${miw.jwt.expire}") int expire) {
        this.secret = secret;
        this.issuer = issuer;
        this.expire = expire;
    }

    public String extractBearerToken(String bearer) {
        if (bearer != null && bearer.startsWith(BEARER) && 3 == bearer.split("\\.").length) {
            return bearer.substring(BEARER.length());
        } else {
            return "";
        }
    }

    public String createToken(String user, String name, String role) {
        return JWT.create()
                .withIssuer(this.issuer)
                .withIssuedAt(new Date())
                .withNotBefore(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + this.expire * 1000))
                .withClaim(USER_CLAIM, user)
                .withClaim(NAME_CLAIM, name)
                .withClaim(ROLE_CLAIM, role)
                .sign(Algorithm.HMAC256(this.secret));
    }

    public String user(String authorization) {
        return this.verify(authorization)
                .map(jwt -> jwt.getClaim(USER_CLAIM).asString())
                .orElse("");
    }

    public String name(String authorization) {
        return this.verify(authorization)
                .map(jwt -> jwt.getClaim(NAME_CLAIM).asString())
                .orElse("");
    }

    public String role(String authorization) {
        return this.verify(authorization)
                .map(jwt -> jwt.getClaim(ROLE_CLAIM).asString())
                .orElse("");
    }

    private Optional<DecodedJWT> verify(String token) {
        try {
            return Optional.of(JWT.require(Algorithm.HMAC256(this.secret))
                    .withIssuer(this.issuer).build()
                    .verify(token));
        } catch (Exception exception) {
            return Optional.empty();
        }
    }

}
