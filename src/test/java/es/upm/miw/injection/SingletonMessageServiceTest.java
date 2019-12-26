package es.upm.miw.injection;

import es.upm.miw.TestConfig;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

// @ExtendWith(SpringExtension.class)
// @SpringBootTest
// @TestPropertySource(locations = "classpath:test.properties")
@TestConfig
public class SingletonMessageServiceTest {

    @Autowired
    private SingletonMessageService singletonMessageService;

    @Test
    public void testGetMessage() {
        assertTrue(singletonMessageService.getMessage().length() > 0);
        LogManager.getLogger(this.getClass()).debug("===>>> message: " + singletonMessageService.getMessage());
    }
}
