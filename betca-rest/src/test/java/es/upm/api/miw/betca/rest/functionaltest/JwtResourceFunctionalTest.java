package es.upm.api.miw.betca.rest.functionaltest;

import es.upm.api.miw.betca.rest.configuration.JwtService;
import es.upm.api.miw.betca.rest.resources.Dto;
import es.upm.api.miw.betca.rest.resources.Gender;
import es.upm.api.miw.betca.rest.resources.TokenDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static es.upm.api.miw.betca.rest.resources.JwtResource.ID_ID;
import static es.upm.api.miw.betca.rest.resources.JwtResource.JWT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class JwtResourceFunctionalTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private JwtService jwtService;

    private String token;

    private String login(String mobile, String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(mobile, password);
        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<TokenDto> response = restTemplate.exchange(
                "http://localhost:" + port + JWT,
                HttpMethod.POST,
                request,
                TokenDto.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNotNull(response.getBody());
        return response.getBody().getToken();
    }

    @BeforeEach
    void before() {
        this.token = this.login("1", "123456");
    }

    @Test
    void testReadByIdWithAdmin() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(this.token);
        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<Void> response = restTemplate.exchange(
                "http://localhost:" + port + JWT + ID_ID.replace("{id}", "666"),
                HttpMethod.GET,
                request,
                Void.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testReadByIdWithCustomer() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(this.token);
        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<Void> response = restTemplate.exchange(
                "http://localhost:" + port + JWT + ID_ID.replace("{id}", "666"),
                HttpMethod.GET,
                request,
                Void.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testReadByIdWithoutTokenUnauthorized() {
        ResponseEntity<Void> response = restTemplate.getForEntity(
                "http://localhost:" + port + JWT + ID_ID.replace("{id}", "666"),
                Void.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    void testUpdate() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(this.login("2", "123456"));
        Dto dto = new Dto(666, "daemon", Gender.FEMALE, LocalDateTime.now(), BigDecimal.TEN);
        HttpEntity<Dto> request = new HttpEntity<>(dto, headers);

        ResponseEntity<Void> response = restTemplate.exchange(
                "http://localhost:" + port + JWT + ID_ID.replace("{id}", "666"),
                HttpMethod.PUT,
                request,
                Void.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testUpdateUnauthorized() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(this.token);
        Dto dto = new Dto(666, "daemon", Gender.FEMALE, LocalDateTime.now(), BigDecimal.TEN);
        HttpEntity<Dto> request = new HttpEntity<>(dto, headers);

        ResponseEntity<Void> response = restTemplate.exchange(
                "http://localhost:" + port + JWT + ID_ID.replace("{id}", "666"),
                HttpMethod.PUT,
                request,
                Void.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }
}
