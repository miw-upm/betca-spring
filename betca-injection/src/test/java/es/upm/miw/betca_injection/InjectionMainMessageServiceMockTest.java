package es.upm.miw.betca_injection;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.anyInt;

@SpringBootTest
class InjectionMainMessageServiceMockTest {

    @MockBean  //https://site.mockito.org/
    private SingletonMessageService singletonMessageService;

    @Autowired
    private InjectionMain mainInjection;

    @Test
    void testCreateMessage() {
        BDDMockito.given(this.singletonMessageService.crateMessage()).willReturn("ok");

        assertEquals("ok", this.mainInjection.createMessage());
    }

    @Test
    void testCreateValue() {
        BDDMockito.given(this.singletonMessageService.createValue(anyInt()))
                .willAnswer(arguments -> arguments.getArgument(0)); // devuelve el propio argumento

        assertEquals(10, this.mainInjection.createValue());
    }
}
