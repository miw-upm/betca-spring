package es.upm.miw.reactor;

import es.upm.miw.ApiTestConfig;
import es.upm.miw.rest_controllers.Dto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;

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
                .expectBody(String.class)
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
    void testReadFlux() {
        this.webTestClient
                .get().uri(WEB_FLUX + WebFluxResource.FLUX)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Dto.class)
                .value(response -> {
                            assertEquals(5, response.size());
                            assertEquals(new Integer(0), response.get(0).getId());
                        }
                );
    }

    @Test
    void testReadFluxEmpty() {
        this.webTestClient
                .get().uri(WEB_FLUX + WebFluxResource.FLUX_EMPTY)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Dto.class)
                .value(response -> response.isEmpty());
    }

    @Test
    void testReadFluxError() {
        this.webTestClient
                .get().uri(WEB_FLUX + WebFluxResource.FLUX_ERROR)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }
}
