package es.upm.miw.betca.injection;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.anyInt;

@TestConfig
class ResourceMessageServiceMockTest {

    @MockitoBean   //https://site.mockito.org/
    private MessageService messageService;

    @Autowired
    private Resource mainInjection;

    @Test
    void testCreateMessage() {
        BDDMockito.given(this.messageService.crateMessage()).willReturn("ok");

        assertEquals("ok", this.mainInjection.createMessage());
    }

    @Test
    void testCreateValue() {
        BDDMockito.given(this.messageService.runValue(anyInt()))
                .willAnswer(arguments -> arguments.getArgument(0)); // devuelve el propio argumento

        assertEquals(10, this.mainInjection.createValue());
    }
}
