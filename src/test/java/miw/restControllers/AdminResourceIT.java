package miw.restControllers;

import miw.persistence.jpa.entities.Gender;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ApiTestConfig
class AdminResourceIT {

    @Value("${local.server.port}")
    private int port = 0;

    @Test
    void testState() {
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + AdminResource.ADMINS + AdminResource.STATE).build().encode()
                .toUri();
        RequestEntity<Object> requestEntity = new RequestEntity<>(HttpMethod.GET, uri);

        ResponseEntity<String> responseEntity = new RestTemplate().exchange(requestEntity, String.class);
        String response = responseEntity.getBody();

        LogManager.getLogger("BETCA-spring: /admins...").info("Response: " + response);
        assertEquals("{\"state\":\"ok\"}", response);
    }

    @Test
    void testStateRestBuilder() {
        String json = new RestBuilder<String>(port).clazz(String.class).path(AdminResource.ADMINS).path(AdminResource.STATE).get().log().build();
        assertEquals("{\"state\":\"ok\"}", json);
    }

    // Parametros y cuerpo --------------------------------------------------------------------------------
    @Test
    void testParamEcho() {
        String json = new RestBuilder<String>(port).clazz(String.class).path(AdminResource.ADMINS).path(AdminResource.ECHO)
                .path(AdminResource.ID).expand(666).param("param", "paaaaram").header("token", "toooken").get().build();
        assertEquals("{\"id\":666,\"token\":\"toooken\",\"param\":\"paaaaram\"}", json);
    }

    @Test
    void testBodyEcho() {
        Dto dto = new Dto(666, "daemon", Gender.FEMALE, LocalDateTime.now());
        Dto response = new RestBuilder<Dto>(port).clazz(Dto.class).path(AdminResource.ADMINS).path(AdminResource.BODY).body(dto).post()
                .build();
        assertEquals(dto, response);
    }

    @Test
    void testBodyStringList() {
        List<String> response = Arrays.asList(new RestBuilder<String[]>(port).path(AdminResource.ADMINS).path(AdminResource.BODY)
                .path(AdminResource.STRING_LIST).clazz(String[].class).get().build());
        assertEquals(3, response.size());
    }

    @Test
    void testBodyDtoList() {
        List<Dto> response = Arrays.asList(new RestBuilder<Dto[]>(port).path(AdminResource.ADMINS).path(AdminResource.BODY)
                .path(AdminResource.DTO_LIST).clazz(Dto[].class).get().build());
        assertEquals(3, response.size());
    }

    @Test
    void testUpdate() {
        Dto dto = new Dto(666, "daemon", Gender.FEMALE, LocalDateTime.now());
        Dto response = new RestBuilder<Dto>(port).clazz(Dto.class).path(AdminResource.ADMINS).path(AdminResource.ECHO)
                .path(AdminResource.ID).expand(999).body(dto).put().build();
        assertEquals(new Integer(999), response.getId());
    }

}
