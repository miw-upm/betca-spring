package es.upm.miw.betca_rest.resources;

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

import static es.upm.miw.betca_rest.resources.BasicAuthResource.BASIC_AUTH;
import static es.upm.miw.betca_rest.resources.BasicResource.ID_ID;
import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@TestPropertySource(locations = "classpath:test.properties")
class BasicAuthResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testReadById() {
        this.webTestClient
                .mutate().filter(basicAuthentication("customer", "123456")).build()
                .get().uri(BASIC_AUTH + ID_ID, 666)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testReadByIdUnauthorizedException() {
        this.webTestClient
                .mutate().filter(basicAuthentication("customer", "kk")).build()
                .get().uri(BASIC_AUTH + ID_ID, 666)
                .exchange()
                .expectStatus().isUnauthorized();
    }

    @Test
    void testCreateWithAdmin() {
        this.webTestClient
                .mutate().filter(basicAuthentication("admin", "123456")).build()
                .post().uri(BASIC_AUTH)
                .body(Mono.just(new Dto(666, "daemon", Gender.FEMALE, LocalDateTime.now())), Dto.class)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testCreateWithOperator() {
        this.webTestClient
                .mutate().filter(basicAuthentication("operator", "123456")).build()
                .post().uri(BASIC_AUTH)
                .body(Mono.just(new Dto(666, "daemon", Gender.FEMALE, LocalDateTime.now())), Dto.class)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testCreateUnauthorizedException() {
        this.webTestClient
                .mutate().filter(basicAuthentication("customer", "123456")).build()
                .post().uri(BASIC_AUTH)
                .body(Mono.just(new Dto(666, "daemon", Gender.FEMALE, LocalDateTime.now())), Dto.class)
                .exchange()
                .expectStatus().isUnauthorized();
    }

}
