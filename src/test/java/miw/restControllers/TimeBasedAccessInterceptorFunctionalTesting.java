package miw.restControllers;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ApiTestConfig
public class TimeBasedAccessInterceptorFunctionalTesting {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Value("${local.server.port}")
    private int port;

    @Test
    public void testOutOfTime() {
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () ->
                new RestBuilder<String>(port).path(AdminResource.ADMINS).path(AdminResource.OUT_OF_TIME)
                        .clazz(String.class).get().build()
        );
        assertEquals(HttpStatus.FORBIDDEN, exception.getStatusCode());
    }

}
