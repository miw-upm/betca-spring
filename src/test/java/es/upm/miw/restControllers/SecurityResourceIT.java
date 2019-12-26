package es.upm.miw.restControllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ApiTestConfig
class SecurityResourceIT {

    @Value("${local.server.port}")
    private int port = 0;

    @Test
    void testAllUserOK() {
        new RestBuilder<>(port).basicAuth("user", "123456")
                .path(SecurityResource.SECURITY).path(SecurityResource.ALL)
                .get().build();
    }

    @Test
    void testUserOK() {
        new RestBuilder<>(port).basicAuth("user", "123456")
                .path(SecurityResource.SECURITY).path(SecurityResource.USER)
                .get().build();
    }

    @Test
    void testUserOtherUserOK() {
        new RestBuilder<>(port).basicAuth("manager", "123456")
                .path(SecurityResource.SECURITY).path(SecurityResource.USER)
                .get().build();
    }

    @Test
    void testManagerOK() {
        new RestBuilder<>(port).basicAuth("manager", "123456")
                .path(SecurityResource.SECURITY).path(SecurityResource.MANAGER)
                .get().build();
    }

    @Test
    void testAdminOK() {
        new RestBuilder<>(port).basicAuth("admin", "123456")
                .path(SecurityResource.SECURITY).path(SecurityResource.ADMIN)
                .get().build();
    }

    @Test
    void testAdminUnauthorizedError() {
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () ->
                new RestBuilder<>(port)
                        .path(SecurityResource.SECURITY).path(SecurityResource.ADMIN)
                        .get().build()
        );
        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatusCode());
    }

    @Test
    void testAdminUnauthorizedPassError() {
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () ->
                new RestBuilder<>(port).basicAuth("admin", "kk")
                        .path(SecurityResource.SECURITY).path(SecurityResource.ADMIN)
                        .get().build()
        );
        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatusCode());
    }

    @Test
    void testAdminUnauthorizedUserError() {
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () ->
                new RestBuilder<>(port).basicAuth("kk", "kk")
                        .path(SecurityResource.SECURITY).path(SecurityResource.ADMIN)
                        .get().build()
        );
        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatusCode());
    }

    @Test
    void testAdminUnauthorizedUser() {
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () ->
                new RestBuilder<>(port).basicAuth("user", "123456")
                        .path(SecurityResource.SECURITY).path(SecurityResource.ADMIN)
                        .get().build()
        );
        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatusCode());
    }

}
