package es.upm.api.miw.betca.injection;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.anyInt;

@TestConfig
class Resource1Service1MockTest {

    @MockitoBean   //https://site.mockito.org/
    private Service1 service1;

    @Autowired
    private Resource1 mainInjection;

    @Test
    void testCreateMessage() {
        BDDMockito.given(this.service1.crateMessage()).willReturn("ok");

        assertEquals("ok", this.mainInjection.createMessage());
    }

    @Test
    void testCreateValue() {
        BDDMockito.given(this.service1.runValue(anyInt()))
                .willAnswer(arguments -> arguments.getArgument(0)); // devuelve el propio argumento

        assertEquals(10, this.mainInjection.createValue());
    }
}
