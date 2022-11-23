package es.upm.miw.betca_rest.resources;

import es.upm.miw.betca_rest.configuration.JwtService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static es.upm.miw.betca_rest.resources.JwtResource.JWT;
import static es.upm.miw.betca_rest.resources.ReactiveBasicResource.ID_ID;
import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@TestPropertySource(locations = "classpath:test.properties")
class JwtResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private JwtService jwtService;

    private String token;

    private String login(String mobile, String password) {
        return this.webTestClient
                .mutate().filter(basicAuthentication(mobile, password)).build()
                .post().uri(JWT)
                .exchange()
                .expectStatus().isOk()
                .expectBody(TokenDto.class)
                .value(Assertions::assertNotNull)
                .returnResult().getResponseBody().getToken();
    }

    @BeforeEach
    void before(){
        this.token = this.login("1", "123456");
    }

    @Test
    void testReadByIdWithAdmin() {
        this.webTestClient
                .mutate().defaultHeader("Authorization", "Bearer " + this.token).build()
                .get().uri(JWT + ID_ID, 666)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testReadByIdWithCustomer() {
        this.webTestClient
                .mutate().defaultHeader("Authorization", "Bearer " + this.token).build()
                .get().uri(JWT + ID_ID, 666)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testReadByIdWithoutTokenUnauthorized() {
        this.webTestClient
                .get().uri(JWT + ID_ID, 666)
                .exchange()
                .expectStatus().isUnauthorized();
    }

    @Test
    void testUpdate() {
        this.webTestClient
                .mutate().defaultHeader("Authorization", "Bearer " + this.login("2", "123456")).build()
                .put().uri(JWT + ID_ID, 666)
                .body(Mono.just(new Dto(666, "daemon", Gender.FEMALE, LocalDateTime.now(), BigDecimal.TEN)), Dto.class)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdateUnauthorized() {
        this.webTestClient
                .mutate().defaultHeader("Authorization", "Bearer " + this.token).build()
                .put().uri(JWT + ID_ID, 666)
                .body(Mono.just(new Dto(666, "daemon", Gender.FEMALE, LocalDateTime.now(), BigDecimal.TEN)), Dto.class)
                .exchange()
                .expectStatus().isUnauthorized();
    }

}
