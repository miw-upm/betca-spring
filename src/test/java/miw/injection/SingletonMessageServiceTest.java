package miw.injection;

import miw.TestConfig;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

// @ExtendWith(SpringExtension.class)
// @SpringBootTest
// @TestPropertySource(locations = "classpath:test.properties")
@TestConfig
public class SingletonMessageServiceTest {

    @Test
    public void testGetMessage(@Autowired SingletonMessageService singletonMessageService) {
        assertTrue(singletonMessageService.getMessage().length() > 0);
        LogManager.getLogger(this.getClass()).info("===>>> message: " + singletonMessageService.getMessage());
    }
}
