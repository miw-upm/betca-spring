package miw.restControllers.jwt;

import miw.restControllers.ApiTestConfig;
import miw.restControllers.RestBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.*;

@ApiTestConfig
class JwtResourceIT {

    @Value("${local.server.port}")
    private int port = 0;

    private String jwt;

    @BeforeEach
    void login() {
        this.jwt = new RestBuilder<String>(port).clazz(String.class).basicAuth("user", "123456")
                .path(JwtResource.JWTS).path(JwtResource.TOKEN).post().build();
    }

    @Test
    void testGetToken() {
        assertNotNull(this.jwt);
    }

    @Test
    void testGetTokenNonAuthBasic() {
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () ->
                new RestBuilder<String>(port).clazz(String.class).path(JwtResource.JWTS).path(JwtResource.TOKEN)
                        .post().build());
        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatusCode());
    }


    @Test
    void testVerify() {
        String json = new RestBuilder<String>(port).clazz(String.class).header("Authorization", "Bearer " + this.jwt)
                .path(JwtResource.JWTS).get().build();
        assertNotNull(json);
    }

}
