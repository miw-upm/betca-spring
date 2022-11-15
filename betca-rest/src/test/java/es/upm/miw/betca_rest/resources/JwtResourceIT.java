package es.upm.miw.betca_rest.resources;

import es.upm.miw.betca_rest.configuration.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static es.upm.miw.betca_rest.resources.ReactiveBasicResource.ID_ID;
import static es.upm.miw.betca_rest.resources.JwtResource.JWT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@TestPropertySource(locations = "classpath:test.properties")
class JwtResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private JwtService jwtService;

    @Test
    void testReadByIdWithAdmin() {
        String token = this.jwtService.createToken("123", "admin", "ADMIN");
        this.webTestClient
                .mutate().defaultHeader("Authorization", "Bearer " + token).build()
                .get().uri(JWT + ID_ID, 666)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testReadByIdWithCustomer() {
        String token = this.jwtService.createToken("123", "customer", "CUSTOMER");
        this.webTestClient
                .mutate().defaultHeader("Authorization", "Bearer " + token).build()
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
    void testCreate() {
        String token = this.jwtService.createToken("123", "operator", "OPERATOR");
        this.webTestClient
                .mutate().defaultHeader("Authorization", "Bearer " + token).build()
                .post().uri(JWT)
                .body(Mono.just(new Dto(666, "daemon", Gender.FEMALE, LocalDateTime.now())), Dto.class)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testCreateUnauthorized() {
        String token = this.jwtService.createToken("123", "customer", "CUSTOMER");
        this.webTestClient
                .mutate().defaultHeader("Authorization", "Bearer " + token).build()
                .post().uri(JWT)
                .body(Mono.just(new Dto(666, "daemon", Gender.FEMALE, LocalDateTime.now())), Dto.class)
                .exchange()
                .expectStatus().isUnauthorized();
    }

}
