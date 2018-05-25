package miw.injection;

import miw.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;


@TestConfig
public class SingletonMessageServiceTest {

    @Autowired
    private SingletonMessageService singletonMessageService;

    @Test
    public void testGetMessage() {
        assertTrue(this.singletonMessageService.getMessage().length() > 0);
    }
}
