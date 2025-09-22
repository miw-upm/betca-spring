package es.upm.api.miw.betca.injection;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.anyInt;

@ContextConfiguration(classes = Application.class)
@SpringBootTest
@ActiveProfiles("test")
class Resource1Service1MockTest {

    @MockitoBean   //https://site.mockito.org/
    private Service1 service1;

    @Autowired
    private Resource1 mainInjection;

    @Test
    void testCreateMessage() {
        BDDMockito.given(this.service1.crateMessage()).willReturn("ok");

        String result = this.mainInjection.createMessage();

        BDDMockito.then(this.service1).should().crateMessage(); // Verifica que fue llamado
        assertThat(result).isEqualTo("ok");
    }

    @Test
    void testCreateValue() {
        BDDMockito.given(this.service1.runValue(anyInt()))
                .willAnswer(arguments -> arguments.getArgument(0)); // devuelve el propio argumento

        int result = this.mainInjection.createValue();

        BDDMockito.then(this.service1).should().runValue(10); // Verifica que fue llamado
        assertThat(result).isEqualTo(10);
    }
}
