package miw.restControllers;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ApiTestConfig
public class SecurityResourceFunctionalTesting {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Value("${local.server.port}")
    private int port;

    @Test
    public void testUserOK() {
        new RestBuilder<>(port).path(SecurityResource.SECURITY).path(SecurityResource.USER).basicAuth("user", "123456").get().build();
    }

    @Test
    public void testUserOtherUserOK() {
        new RestBuilder<>(port).path(SecurityResource.SECURITY).path(SecurityResource.USER).basicAuth("manager", "123456").get().build();
    }

    @Test
    public void testManagerOK() {
        new RestBuilder<>(port).path(SecurityResource.SECURITY).path(SecurityResource.MANAGER).basicAuth("manager", "123456").get().build();
    }

    @Test
    public void testAdminOK() {
        new RestBuilder<>(port).path(SecurityResource.SECURITY).path(SecurityResource.ADMIN).basicAuth("admin", "123456").get().build();
    }

    @Test
    public void testAdminUnauthorizedPassError() {
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () ->
                new RestBuilder<>(port).path(SecurityResource.SECURITY).path(SecurityResource.ADMIN).basicAuth("admin", "kk").get().build()
        );
        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatusCode());
    }

    @Test
    public void testAdminUnauthorizedUser() {
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () ->
                new RestBuilder<>(port).path(SecurityResource.SECURITY).path(SecurityResource.ADMIN)
                        .basicAuth("user", "123456").get().build()
        );
        assertEquals(HttpStatus.FORBIDDEN, exception.getStatusCode());
    }

    @Test
    public void testUserUnauthorizedNonUser() {
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () ->
                new RestBuilder<>(port).path(SecurityResource.SECURITY).path(SecurityResource.USER).get().build()
        );
        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatusCode());
    }

    @Test
    public void testManagerUnauthorizedUser() {
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> new RestBuilder<>(port).path(SecurityResource.SECURITY).path(SecurityResource.MANAGER)
                        .basicAuth("user", "123456").get().build()
        );
        assertEquals(HttpStatus.FORBIDDEN, exception.getStatusCode());
    }

}
