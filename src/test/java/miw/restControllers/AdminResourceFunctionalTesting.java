package miw.restControllers;

import miw.persistence.jpa.entities.Gender;
import org.apache.logging.log4j.LogManager;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
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
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ApiTestConfig
public class AdminResourceFunctionalTesting {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Value("${local.server.port}")
    private int port;

    @Test
    public void testState() {
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + AdminResource.ADMINS + AdminResource.STATE).build().encode()
                .toUri();
        RequestEntity<Object> requestEntity = new RequestEntity<>(HttpMethod.GET, uri);

        ResponseEntity<String> responseEntity = new RestTemplate().exchange(requestEntity, String.class);
        String response = responseEntity.getBody();

        LogManager.getLogger("BETCA-spring: /admins...").info("Response: " + response);
        assertEquals("{\"state\":\"ok\"}", response);
    }

    @Test
    public void testStateRestBuilder() {
        String json = new RestBuilder<String>(port).clazz(String.class).path(AdminResource.ADMINS).path(AdminResource.STATE).get().build();
        assertEquals("{\"state\":\"ok\"}", json);
    }

    @Test
    public void testOutOfTime() {
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () ->
                new RestBuilder<>(port).path(AdminResource.ADMINS).path(AdminResource.OUT_OF_TIME).get().build()
        );
        assertEquals(HttpStatus.FORBIDDEN, exception.getStatusCode());
    }

    // Parametros y cuerpo
    @Test
    public void testParamEcho() {
        String json = new RestBuilder<String>(port).clazz(String.class).path(AdminResource.ADMINS).path(AdminResource.ECHO)
                .path(AdminResource.ID).expand(666).param("param", "paaaaram").header("token", "toooken").get().build();
        assertEquals("{\"id\":666,\"token\":\"toooken\",\"param\":\"paaaaram\"}", json);
    }

    @Test
    public void testBodyEcho() {
        Dto dto = new Dto(666, "daemon", Gender.FEMALE, LocalDateTime.now());
        Dto response = new RestBuilder<Dto>(port).clazz(Dto.class).path(AdminResource.ADMINS).path(AdminResource.BODY).body(dto).post()
                .build();
        assertEquals(dto, response);
    }

    @Test
    public void testBodyStringList() {
        List<String> response = Arrays.asList(new RestBuilder<String[]>(port).path(AdminResource.ADMINS).path(AdminResource.BODY)
                .path(AdminResource.STRING_LIST).clazz(String[].class).get().build());
        assertEquals(3, response.size());
    }

    @Test
    public void testBodyDtoList() {
        List<Dto> response = Arrays.asList(new RestBuilder<Dto[]>(port).path(AdminResource.ADMINS).path(AdminResource.BODY)
                .path(AdminResource.DTO_LIST).clazz(Dto[].class).get().build());
        assertEquals(3, response.size());
    }

    @Test
    public void testUpdate() {
        Dto dto = new Dto(666, "daemon", Gender.FEMALE, LocalDateTime.now());
        Dto response = new RestBuilder<Dto>(port).clazz(Dto.class).path(AdminResource.ADMINS).path(AdminResource.ECHO)
                .path(AdminResource.ID).expand(999).body(dto).put().build();
        assertEquals(new Integer(999), response.getId());
    }

    // Exceptions
    @Test
    public void testErrorNotToken() {
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () ->
                new RestBuilder<Dto>(port).path(AdminResource.ADMINS).path(AdminResource.ERROR).path(AdminResource.ID)
                        .expand(66).get().build()
        );
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    public void testErrorMalFormedToken() {
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () ->
                new RestBuilder<Dto>(port).path(AdminResource.ADMINS).path(AdminResource.ERROR).path(AdminResource.ID)
                        .expand(66).header("token", "kk").get().build()
        );
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    public void testErrorNotExistToken() {
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () ->
                new RestBuilder<Dto>(port).path(AdminResource.ADMINS).path(AdminResource.ERROR).path(AdminResource.ID)
                        .expand(66).header("token", "Basic kk").get().build()
        );
        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatusCode());
    }

    @Test
    public void testErrorNotExistId() {
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () ->
                new RestBuilder<Dto>(port).path(AdminResource.ADMINS).path(AdminResource.ERROR).path(AdminResource.ID)
                        .expand(0).header("token", "Basic good").get().build()
        );
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    public void testErrorOk() {
        Dto response = new RestBuilder<Dto>(port).path(AdminResource.ADMINS).path(AdminResource.ERROR).path(AdminResource.ID).expand(666)
                .header("token", "Basic good").clazz(Dto.class).get().build();
        assertEquals(new Integer(666), response.getId());
    }

    @Test
    public void test() {
        List<String> list = Arrays.asList("u", "dos", "tres");
        Stream<String> list5 = Stream.of("cuatro", "cinco", "seis");

        for (String item : list) {
            System.out.println(item);
        }

        list.forEach(item -> System.out.println(item));

        Stream<String> list2 = list.stream().map(item -> {
            return item + "--";
        });

        list2.forEach(item -> System.out.println(item));

        Stream<String> list3 = list.stream().filter(item -> item.length() < 2);

        list3.forEach(item -> System.out.println(item));

        List<String> list4 = list5.collect(Collectors.toList());

        list4.forEach(this::method);
    }

    private void method(String item) {
        System.out.println(">>> " + item);
    }

    //
    // @Test
    // public void testSecurityAnnotationOk() {
    // String response = new RestBuilder<String>(URL_API).path(Uris.ADMINS).path(Uris.SECURITY).basicAuth("admin", "123456")
    // .clazz(String.class).get().build();
    // System.out.println("INFO >>>>> " + response);
    // }
    //
    // @Test
    // public void testSecurityAnnotationForbidden() {
    // try {
    // new RestBuilder<String>(URL_API).path(Uris.ADMINS).path(Uris.SECURITY).basicAuth("user", "123456").clazz(String.class).get()
    // .build();
    // fail();
    // } catch (HttpClientErrorException httpError) {
    // assertEquals(HttpStatus.FORBIDDEN, httpError.getStatusCode());
    // System.out.println("ERROR >>>>> " + httpError.getMessage());
    // System.out.println("ERROR >>>>> " + httpError.getResponseBodyAsString());
    // }
    // }
    //
    // @Test
    // public void testSecurityAnnotationUnauthorized() {
    // try {
    // new RestBuilder<String>(URL_API).path(Uris.ADMINS).path(Uris.SECURITY).basicAuth("user", "kkk").clazz(String.class).get()
    // .build();
    // fail();
    // } catch (HttpClientErrorException httpError) {
    // assertEquals(HttpStatus.UNAUTHORIZED, httpError.getStatusCode());
    // System.out.println("ERROR >>>>> " + httpError.getMessage());
    // System.out.println("ERROR >>>>> " + httpError.getResponseBodyAsString());
    // }
    // }
}
