package es.upm.miw.restControllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ApiTestConfig
class ExceptionResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testErrorNotToken() {
        this.webTestClient
                .get().uri(uriBuilder -> uriBuilder
                .path(ExceptionResource.EXCEPTIONS + ExceptionResource.ERROR + AdminResource.ID)
                .queryParam("param", "good").build(66))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testErrorMalFormedToken() {
        this.webTestClient
                .get().uri(uriBuilder -> uriBuilder
                .path(ExceptionResource.EXCEPTIONS + ExceptionResource.ERROR + AdminResource.ID)
                .queryParam("param", "good").build(66))
                .header("token", "kk")
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testErrorIdFormat() {
        this.webTestClient
                .get().uri(uriBuilder -> uriBuilder
                .path(ExceptionResource.EXCEPTIONS + ExceptionResource.ERROR + AdminResource.ID)
                .queryParam("param", "good").build("kk"))
                .header("token", "Basic good")
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testErrorNotExistId() {
        this.webTestClient
                .get().uri(uriBuilder -> uriBuilder
                .path(ExceptionResource.EXCEPTIONS + ExceptionResource.ERROR + AdminResource.ID)
                .queryParam("param", "good").build(0))
                .header("token", "Basic good")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testErrorLostParam() {
        this.webTestClient
                .get().uri(uriBuilder -> uriBuilder
                .path(ExceptionResource.EXCEPTIONS + ExceptionResource.ERROR + AdminResource.ID).build(66))
                .header("token", "Basic good")
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testErrorInvalidParam() {
        this.webTestClient
                .get().uri(uriBuilder -> uriBuilder
                .path(ExceptionResource.EXCEPTIONS + ExceptionResource.ERROR + AdminResource.ID)
                .queryParam("param", "").build(66))
                .header("token", "Basic good")
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testErrorConflictParam() {
        this.webTestClient
                .get().uri(uriBuilder -> uriBuilder
                .path(ExceptionResource.EXCEPTIONS + ExceptionResource.ERROR + AdminResource.ID)
                .queryParam("param", "kk").build(66))
                .header("token", "Basic good")
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testOk() {
        Dto response = this.webTestClient
                .get().uri(uriBuilder -> uriBuilder
                        .path(ExceptionResource.EXCEPTIONS + ExceptionResource.ERROR + AdminResource.ID)
                        .queryParam("param", "good").build(66))
                .header("token", "Basic good")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Dto.class)
                .returnResult().getResponseBody();
        assertNotNull(response);
        assertEquals(new Integer(666), response.getId());
    }

    @Test
    void testMyFilter() {
        this.webTestClient
                .get().uri(ExceptionResource.EXCEPTIONS + ExceptionResource.MY_FILTER)
                .exchange()
                .expectStatus().isOk();
    }


    @Test
    void testOutOfTime() {
        this.webTestClient
                .get().uri(ExceptionResource.EXCEPTIONS + ExceptionResource.OUT_OF_TIME)
                .exchange()
                .expectStatus().isForbidden();
    }


}
