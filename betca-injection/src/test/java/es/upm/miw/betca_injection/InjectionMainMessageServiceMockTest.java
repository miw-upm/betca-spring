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
    void testGetMessage() {
        BDDMockito.given(this.singletonMessageService.getMessage()).willReturn("ok");

        assertEquals("ok", this.mainInjection.getMessage());
    }

    @Test
    void testGetValue() {
        BDDMockito.given(this.singletonMessageService.getValue(anyInt()))
                .willAnswer(arguments -> arguments.getArgument(0)); // devuelve el propio argumento

        assertEquals(10, this.mainInjection.getValue());
    }
}
