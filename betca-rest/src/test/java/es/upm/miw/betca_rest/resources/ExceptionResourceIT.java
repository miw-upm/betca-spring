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

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static es.upm.miw.betca_rest.resources.ReactiveBasicResource.ID_ID;
import static es.upm.miw.betca_rest.resources.ReactiveExceptionResource.REACTIVE_EXCEPTIONS;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@TestPropertySource(locations = "classpath:test.properties")
public class ExceptionResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testReadNotFoundException() {
        this.webTestClient
                .get().uri(REACTIVE_EXCEPTIONS + ID_ID, 0)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testReadIdErrorBadRequestException() {
        this.webTestClient
                .get().uri(REACTIVE_EXCEPTIONS + ID_ID, "kk") // ServerWebInputException
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testReadLargeIDBadRequestException() {
        this.webTestClient
                .get().uri(REACTIVE_EXCEPTIONS + ID_ID, 9999999999L)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateMissingBodyBadRequestException() {
        this.webTestClient
                .post().uri(REACTIVE_EXCEPTIONS)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateIdError() {
        this.webTestClient
                .post().uri(REACTIVE_EXCEPTIONS)
                .body(Mono.just(new Dto(5, "daemon", Gender.FEMALE, LocalDateTime.now(), BigDecimal.TEN)), Dto.class)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateNameError() {
        this.webTestClient
                .post().uri(REACTIVE_EXCEPTIONS)
                .body(Mono.just(new Dto(20, " ", Gender.FEMALE, LocalDateTime.now(), BigDecimal.TEN)), Dto.class)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateBornDateError() {
        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
        this.webTestClient
                .post().uri(REACTIVE_EXCEPTIONS)
                .body(Mono.just(new Dto(20, "daemon", Gender.FEMALE, tomorrow, BigDecimal.TEN)), Dto.class)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreatePriceError() {
        this.webTestClient
                .post().uri(REACTIVE_EXCEPTIONS)
                .body(Mono.just(new Dto(20, "daemon", Gender.FEMALE, LocalDateTime.now(), new BigDecimal("-1.0"))), Dto.class)
                .exchange()
                .expectStatus().isBadRequest();
    }
}
