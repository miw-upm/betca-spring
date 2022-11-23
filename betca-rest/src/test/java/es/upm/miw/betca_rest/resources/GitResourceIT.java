package es.upm.miw.betca_rest.resources;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;

import static es.upm.miw.betca_rest.resources.GitResource.GIT;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@TestPropertySource(locations = "classpath:test.properties")
public class GitResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testRead() {
        this.webTestClient
                .mutate()
                .responseTimeout(Duration.ofMillis(50000)).build()
                .get().uri(GIT)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_GATEWAY)
                .expectBody(String.class)
                .value(body -> LogManager.getLogger(this.getClass()).debug(body));
    }


}
