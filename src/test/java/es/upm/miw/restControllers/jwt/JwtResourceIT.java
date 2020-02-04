package es.upm.miw.restControllers.jwt;

import es.upm.miw.restControllers.ApiTestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;

@ApiTestConfig
class JwtResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    private String jwt;

    @BeforeEach
    void login() {
        this.jwt = webTestClient
                .mutate().filter(basicAuthentication("user", "123456")).build()
                .post().uri(JwtResource.JWTS + JwtResource.TOKEN)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult().getResponseBody();
    }

    @Test
    void testGetToken() {
        assertNotNull(this.jwt);
    }

    @Test
    void testGetTokenNotAuthBasic() {
        webTestClient
                .post().uri(JwtResource.JWTS + JwtResource.TOKEN)
                .exchange()
                .expectStatus().isUnauthorized();
    }


    @Test
    void testVerify() {
        webTestClient
                .get().uri(JwtResource.JWTS)
                .header("Authorization", "Bearer " + this.jwt)
                .exchange()
                .expectStatus().isOk();
    }

}
