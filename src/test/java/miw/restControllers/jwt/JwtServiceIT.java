package miw.restControllers.jwt;

import miw.TestConfig;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class JwtServiceIT {

    @Autowired
    private JwtService jwtService;

    private String jwtToken;

    @BeforeEach
    void createToken() {
        this.jwtToken = this.jwtService.createToken("jesus", Arrays.asList("MANAGER,ADMIN"));
    }

    @Test
    void testCreateToken() {
        assertNotNull(this.jwtToken);
        assertEquals(3, this.jwtToken.split("\\.").length);
        LogManager.getLogger(this.getClass()).info("===>>> jwt---" + this.jwtToken + "---");
    }

    @Test
    void testIsBearer() {
        assertTrue(this.jwtService.isBearer(JwtService.BEARER + this.jwtToken));
    }

    @Test
    void testUser() throws JwtException {
        assertEquals("jesus", this.jwtService.user(JwtService.BEARER + this.jwtToken));
    }

    @Test
    void testUserExceptionNonBearer() {
        assertThrows(JwtException.class, () -> this.jwtService.user("kk " + this.jwtToken));
    }

    @Test
    void testUserExceptionNonJwt() {
        assertThrows(JwtException.class, () -> this.jwtService.user(JwtService.BEARER + this.jwtToken + "s"));
    }

    @Test
    void testUserExceptionExpiredJwt() {
        String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9" +
                ".eyJuYmYiOjE1NDc1MzgxMjUsInJvbGVzIjpbIk1BTkFHRVIsQURNSU4iXSwiaXNzIjoibWl3LXNwcmluZzUiLCJleHAiOjE1NDc1"
                + "MzgxMjUsImlhdCI6MTU0NzUzODEyNSwidXNlciI6Implc3VzIn0.Xrxmo-pZATEwTz8CFZ8gxb4sRJbNlOrZSepABCbwhBs";

        assertThrows(JwtException.class, () -> this.jwtService.user(JwtService.BEARER + jwt));
    }


    @Test
    void testRoles() throws JwtException {
        List<String> roleList = this.jwtService.roles(JwtService.BEARER + this.jwtToken);
        assertArrayEquals(new String[]{"MANAGER,ADMIN"}, roleList.toArray());
    }
}
