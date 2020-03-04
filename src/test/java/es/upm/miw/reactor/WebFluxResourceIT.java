package es.upm.miw.reactor;

import es.upm.miw.ApiTestConfig;
import es.upm.miw.rest_controllers.Dto;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;

import static es.upm.miw.reactor.WebFluxResource.WEB_FLUX;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ApiTestConfig
class WebFluxResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testReadMono() {
        this.webTestClient
                .get().uri(WEB_FLUX + WebFluxResource.MONO)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Dto.class)
                .value(dto -> assertEquals(new Integer(666), dto.getId()));
    }

    @Test
    void testReadMonoEmpty() {
        this.webTestClient
                .get().uri(WEB_FLUX + WebFluxResource.MONO_EMPTY)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Dto.class)
                .value(Assertions::assertNull);
    }

    @Test
    void testReadMonoError() {
        this.webTestClient
                .get().uri(WEB_FLUX + WebFluxResource.MONO_ERROR)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testReadFluxList() {
        List<Dto> dtos = this.webTestClient
                .get().uri(WEB_FLUX + WebFluxResource.FLUX_LIST)
                //.accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Dto.class)
                .returnResult().getResponseBody();
        LogManager.getLogger(this.getClass()).debug(dtos);
    }

    @Test
    void testReadFluxEvents() {
        Flux<Dto> dtoFlux = this.webTestClient
                .get().uri(WEB_FLUX + WebFluxResource.FLUX_EVENTS)
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Dto.class).getResponseBody();
        StepVerifier
                .create(dtoFlux)
                .consumeNextWith(LogManager.getLogger(this.getClass())::debug)
                .thenCancel()
                .verify();
    }

}
