package es.upm.miw.betca_rest.resources;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static es.upm.miw.betca_rest.resources.BasicResource.BASIC;
import static es.upm.miw.betca_rest.resources.ReactiveBasicResource.ID_ID;
import static es.upm.miw.betca_rest.resources.ReactiveBasicResource.SEARCH;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@TestPropertySource(locations = "classpath:test.properties")
public class BasicResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testReadByIdJson() {
        String json = this.webTestClient
                .get().uri(BASIC + ID_ID, 666)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult().getResponseBody();
        LogManager.getLogger(this.getClass()).debug("BETCA-spring: /basic/666... Response: " + json);
    }

    @Test
    void testCreate() {
        this.webTestClient
                .post().uri(BASIC)
                .body(Mono.just(new Dto(666, "daemon", Gender.FEMALE, LocalDateTime.now(), BigDecimal.TEN)), Dto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Dto.class)
                .value(Assertions::assertNotNull)
                .value(dto -> {
                    assertEquals(666, dto.getId());
                    assertEquals("daemon", dto.getName());
                    assertEquals(Gender.FEMALE, dto.getGender());
                    assertNotNull(dto.getBornDate());
                });
    }

    @Test
    void testReadById() {
        this.webTestClient
                .get().uri(BASIC + ID_ID, 666)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Dto.class)
                .value(Assertions::assertNotNull)
                .value(dto -> {
                    assertEquals(666, dto.getId());
                    assertEquals("daemon", dto.getName());
                    assertNotNull(dto.getBornDate());
                });
    }

    @Test
    void testDelete() {
        this.webTestClient
                .delete().uri(BASIC + ID_ID, 666)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdate() {
        this.webTestClient
                .put().uri(BASIC + ID_ID, 666)
                .body(Mono.just(new Dto(666, "daemon", Gender.FEMALE, LocalDateTime.now(), BigDecimal.TEN)), Dto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Dto.class)
                .value(Assertions::assertNotNull)
                .value(dto -> {
                    assertEquals(666, dto.getId());
                    assertEquals("daemon", dto.getName());
                    assertEquals(Gender.FEMALE, dto.getGender());
                    assertNotNull(dto.getBornDate());
                });
    }

    @Test
    void testUpdateNames() {
        this.webTestClient
                .patch().uri(BASIC)
                .body(BodyInserters.fromValue(List.of(new UpdatingDto(666, "daemon"), new UpdatingDto(999, "daemon"))))
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Dto.class)
                .value(dtos -> assertTrue(dtos.stream()
                                .allMatch(dto -> "daemon".equals(dto.getName()))
                        )
                );
    }

    @Test
    void testSearch() {
        this.webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(BASIC + SEARCH)
                        .queryParam("name", "Miw")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Dto.class)
                .value(Assertions::assertNotNull)
                .value(dtos -> assertTrue(dtos
                                .stream().allMatch(dto -> "Miw".equals(dto.getName()))
                        )
                );
    }
}
