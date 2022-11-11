package es.upm.miw.betca_injection;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SingletonMessageServiceTest {

    @Autowired
    private SingletonMessageService singletonMessageService;

    @Test
    void testGetMessage() {
        assertTrue(singletonMessageService.getMessage().length() > 0);
        LogManager.getLogger(this.getClass()).info("===>>> message: " + singletonMessageService.getMessage());
    }

    @Test
    void testGetValue() {
        assertEquals(100, singletonMessageService.getValue(10));
        LogManager.getLogger(this.getClass()).info("===>>> value: " + singletonMessageService.getValue(10));
    }
}
