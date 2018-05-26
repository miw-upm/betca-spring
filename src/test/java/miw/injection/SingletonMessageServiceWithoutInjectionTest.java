package miw.injection;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SingletonMessageServiceWithoutInjectionTest {

    private SingletonMessageService singletonMessageService;

    @BeforeEach
    public void before() {
        this.singletonMessageService = new SingletonMessageService();
    }

    @Test
    public void testGetMessage() {
        assertTrue(this.singletonMessageService.getMessage().length() > 0);
        LogManager.getLogger(this.getClass()).info(">>> NOTE, without injection");
        LogManager.getLogger(this.getClass()).info(">>> message: " + this.singletonMessageService.getMessage());
    }
}
