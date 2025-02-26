package es.upm.miw.betca.rest.resources;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static es.upm.miw.betca.rest.resources.BasicAuthResource.BASIC_AUTH;
import static es.upm.miw.betca.rest.resources.BasicAuthResource.ID_ID;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class BasicAuthResourceIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl() {
        return "http://localhost:" + port + BASIC_AUTH;
    }

    private HttpHeaders createHeaders(String username, String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);
        return headers;
    }

    @Test
    void testReadById() {
        String url = baseUrl() + ID_ID.replace("{id}", "666");
        HttpHeaders headers = createHeaders("1", "123456");
        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.GET, request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testReadByIdUnauthorizedException() {
        String url = baseUrl() + ID_ID.replace("{id}", "666");
        HttpHeaders headers = createHeaders("1", "kk");
        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.GET, request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    void testCreateWithAdmin() {
        HttpHeaders headers = createHeaders("3", "123456");
        Dto dto = new Dto(666, "daemon", Gender.FEMALE, LocalDateTime.now(), BigDecimal.TEN);
        HttpEntity<Dto> request = new HttpEntity<>(dto, headers);

        ResponseEntity<Void> response = restTemplate.exchange(baseUrl(), HttpMethod.POST, request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testCreateWithOperator() {
        HttpHeaders headers = createHeaders("2", "123456");
        Dto dto = new Dto(666, "daemon", Gender.FEMALE, LocalDateTime.now(), BigDecimal.TEN);
        HttpEntity<Dto> request = new HttpEntity<>(dto, headers);

        ResponseEntity<Void> response = restTemplate.exchange(baseUrl(), HttpMethod.POST, request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testCreateUnauthorizedException() {
        HttpHeaders headers = createHeaders("4", "123456");
        Dto dto = new Dto(666, "daemon", Gender.FEMALE, LocalDateTime.now(), BigDecimal.TEN);
        HttpEntity<Dto> request = new HttpEntity<>(dto, headers);

        ResponseEntity<Void> response = restTemplate.exchange(baseUrl(), HttpMethod.POST, request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }
}
