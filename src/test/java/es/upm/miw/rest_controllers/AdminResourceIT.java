package es.upm.miw.rest_controllers;

import es.upm.miw.persistence.jpa.entities.Gender;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@TestPropertySource(locations = "classpath:test.properties")
@ActiveProfiles("dev")
class AdminResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdateClient() {
        Dto dto = new Dto(666, "daemon", Gender.FEMALE, LocalDateTime.now());
        Dto response = this.webTestClient
                .mutate().filter(basicAuthentication("user", "123456")).build()
                .put()
                //.uri(AdminResource.ADMINS + AdminResource.ECHO + AdminResource.ID_ID, 999)
                .uri(uriBuilder -> uriBuilder
                        .path(AdminResource.ADMINS + AdminResource.ECHO + AdminResource.ID_ID)
                        .queryParam("param", "value")
                        .build(999)
                )
                .body(BodyInserters.fromObject(dto))
                .header("name", "value1", "value2", "value3")
                .header("Authorization", "Bearer eyJ0eXAiOiJ...")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Dto.class)
                .returnResult().getResponseBody();
        assertNotNull(response);
        assertEquals(new Integer(999), response.getId());
    }

    @Test
    void testState() {
        String json = this.webTestClient
                .get().uri(AdminResource.ADMINS + AdminResource.STATE)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult().getResponseBody();

        LogManager.getLogger("BETCA-spring: /admins...").info("Response: " + json);
        assertEquals("{\"state\":\"ok\"}", json);
    }

    @Test
    void testBodyEcho() {
        Dto dto = new Dto(666, "daemon", Gender.FEMALE, LocalDateTime.now());
        Dto response = this.webTestClient
                .post().uri(AdminResource.ADMINS + AdminResource.BODY)
                .body(BodyInserters.fromObject(dto))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Dto.class)
                .returnResult().getResponseBody();
        assertEquals(dto, response);
    }

    @Test
    void testBodyStringList() {
        String[] response = this.webTestClient
                .get().uri(AdminResource.ADMINS + AdminResource.BODY + AdminResource.STRING_LIST)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String[].class)
                .returnResult().getResponseBody();
        assertArrayEquals(new String[]{"uno", "dos", "tres"}, response);
    }

    @Test
    void testBodyDtoList() {
        List<Dto> response = this.webTestClient
                .get().uri(AdminResource.ADMINS + AdminResource.BODY + AdminResource.DTO_LIST)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Dto.class)
                .returnResult().getResponseBody();
        assertNotNull(response);
        assertEquals(3, response.size());
    }

    @Test
    void testUpdate() {
        Dto dto = new Dto(666, "daemon", Gender.FEMALE, LocalDateTime.now());
        Dto response = this.webTestClient
                .put().uri(AdminResource.ADMINS + AdminResource.ECHO + AdminResource.ID_ID, 999)
                .body(BodyInserters.fromObject(dto))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Dto.class)
                .returnResult().getResponseBody();
        assertNotNull(response);
        assertEquals(new Integer(999), response.getId());
    }

}
