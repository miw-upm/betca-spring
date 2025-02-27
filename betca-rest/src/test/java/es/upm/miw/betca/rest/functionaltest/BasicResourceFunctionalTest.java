package es.upm.miw.betca.rest.functionaltest;

import es.upm.miw.betca.rest.resources.Dto;
import es.upm.miw.betca.rest.resources.Gender;
import es.upm.miw.betca.rest.resources.UpdatingDto;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static es.upm.miw.betca.rest.resources.BasicResource.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class BasicResourceFunctionalTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl() {
        return "http://localhost:" + port + BASIC;
    }

    @Test
    void testReadByIdJson() {
        String url = baseUrl() + ID_ID.replace("{id}", "666");
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        LogManager.getLogger(this.getClass()).debug(() -> "BETCA-spring: /basic/666... Response: " + response.getBody());
    }

    @Test
    void testCreate() {
        Dto dto = new Dto(666, "daemon", Gender.FEMALE, LocalDateTime.now(), BigDecimal.TEN);
        ResponseEntity<Dto> response = restTemplate.postForEntity(baseUrl(), dto, Dto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(666, response.getBody().getId());
        assertEquals("daemon", response.getBody().getName());
        assertEquals(Gender.FEMALE, response.getBody().getGender());
        assertNotNull(response.getBody().getBornDate());
    }

    @Test
    void testReadById() {
        String url = baseUrl() + ID_ID.replace("{id}", "666");
        ResponseEntity<Dto> response = restTemplate.getForEntity(url, Dto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(666, response.getBody().getId());
        assertEquals("daemon", response.getBody().getName());
        assertNotNull(response.getBody().getBornDate());
    }

    @Test
    void testDelete() {
        String url = baseUrl() + ID_ID.replace("{id}", "666");
        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testUpdate() {
        String url = baseUrl() + ID_ID.replace("{id}", "666");
        Dto dto = new Dto(666, "daemon", Gender.FEMALE, LocalDateTime.now(), BigDecimal.TEN);
        ResponseEntity<Dto> response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(dto), Dto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(666, response.getBody().getId());
        assertEquals("daemon", response.getBody().getName());
        assertEquals(Gender.FEMALE, response.getBody().getGender());
        assertNotNull(response.getBody().getBornDate());
    }

    @Test
    void testUpdateNames() {
        List<UpdatingDto> updatingDtos = List.of(new UpdatingDto(666, "daemon"), new UpdatingDto(999, "daemon"));
        ResponseEntity<Dto[]> response = restTemplate.exchange(baseUrl(), HttpMethod.PATCH, new HttpEntity<>(updatingDtos), Dto[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertTrue(Stream.of(Objects.requireNonNull(response.getBody())).allMatch(dto -> "daemon".equals(dto.getName())));
    }

    @Test
    void testSearch() {
        String url = baseUrl() + SEARCH + "?name=Miw";
        ResponseEntity<Dto[]> response = restTemplate.getForEntity(url, Dto[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNotNull(response.getBody());
        assertTrue(Stream.of(response.getBody()).allMatch(dto -> "Miw".equals(dto.getName())));
    }
}