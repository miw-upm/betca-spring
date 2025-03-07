package es.upm.api.domain.services;

import es.upm.api.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class JwtServiceIT {

    @Autowired
    private JwtService jwtService;

    @Test
    void testJwtExceptionNotBearer() {
        assertTrue(jwtService.user("Not Bearer").isEmpty());
    }

    @Test
    void testJwtUtilExtract() {
        assertEquals("t.t.t", jwtService.extractToken("Bearer t.t.t"));
    }

    @Test
    void testCreateTokenAndVerify() {
        String token = jwtService.createToken("user-id", "name", "ROLE");
        assertEquals(3, token.split("\\.").length);
        assertTrue(token.length() > 30);
        assertEquals("user-id", jwtService.user(token));
        assertEquals("name", jwtService.name(token));
        assertEquals("ROLE", jwtService.role(token));
    }

}
