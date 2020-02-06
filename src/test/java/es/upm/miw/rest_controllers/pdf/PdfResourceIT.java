package es.upm.miw.rest_controllers.pdf;

import es.upm.miw.rest_controllers.ApiTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

@ApiTestConfig
class PdfResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testPdfs() {
        webTestClient
                .get().uri(uriBuilder -> uriBuilder
                .path(PdfResource.PDFS).queryParam("title", "BETCA: Spring 5. PDF")
                .queryParam("paragraph", "paragraph")
                .build())
                .exchange()
                .expectStatus().isOk();
    }

}
