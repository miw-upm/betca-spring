package miw.restControllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ApiTestConfig
class ExceptionResourceIT {

    @Value("${local.server.port}")
    private int port = 0;

    @Test
    void testErrorNotToken() {
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () ->
                new RestBuilder<Dto>(port)
                        .path(ExceptionResource.EXCEPTIONS).path(ExceptionResource.ERROR).path(AdminResource.ID).expand(66)
                        .param("param", "good")
                        .get().build()
        );
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testErrorMalFormedToken() {
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () ->
                new RestBuilder<Dto>(port).header("token", "kk")
                        .path(ExceptionResource.EXCEPTIONS).path(ExceptionResource.ERROR).path(AdminResource.ID).expand(66)
                        .param("param", "good")
                        .get().build()
        );
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testErrorIdFormat() {
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () ->
                new RestBuilder<Dto>(port).header("token", "Basic good")
                        .path(ExceptionResource.EXCEPTIONS).path(ExceptionResource.ERROR).path(AdminResource.ID).expand("kk")
                        .param("param", "good")
                        .get().build()
        );
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testErrorNotExistId() {
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () ->
                new RestBuilder<Dto>(port).header("token", "Basic good")
                        .path(ExceptionResource.EXCEPTIONS).path(ExceptionResource.ERROR).path(AdminResource.ID).expand(0)
                        .param("param", "good")
                        .get().build()
        );
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    void testErrorLostParam() {
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () ->
                new RestBuilder<Dto>(port).header("token", "Basic good")
                        .path(ExceptionResource.EXCEPTIONS).path(ExceptionResource.ERROR).path(AdminResource.ID).expand(66)
                        .get().build()
        );
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testErrorInvalidParam() {
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () ->
                new RestBuilder<Dto>(port).header("token", "Basic good")
                        .path(ExceptionResource.EXCEPTIONS).path(ExceptionResource.ERROR).path(AdminResource.ID).expand(66)
                        .param("param", "")
                        .get().build()
        );
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testErrorConflictParam() {
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () ->
                new RestBuilder<Dto>(port).header("token", "Basic good")
                        .path(ExceptionResource.EXCEPTIONS).path(ExceptionResource.ERROR).path(AdminResource.ID).expand(66)
                        .param("param", "kk")
                        .get().build()
        );
        assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
    }

    @Test
    void testOk() {
        Dto response = new RestBuilder<Dto>(port).clazz(Dto.class).header("token", "Basic good")
                .path(ExceptionResource.EXCEPTIONS).path(ExceptionResource.ERROR).path(AdminResource.ID).expand(666)
                .param("param", "good")
                .get().build();
        assertEquals(new Integer(666), response.getId());
    }

    @Test
    void testOutOfTime() {
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () ->
                new RestBuilder<>(port).path(ExceptionResource.EXCEPTIONS).path(ExceptionResource.OUT_OF_TIME).get().build()
        );
        assertEquals(HttpStatus.FORBIDDEN, exception.getStatusCode());
    }


}
