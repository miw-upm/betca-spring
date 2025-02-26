package es.upm.miw.betca.injection;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestConfig
class MessageServiceTest {

    @Autowired
    private MessageService singletonMessageService;

    @Test
    void testCreateMessage() {
        assertFalse(singletonMessageService.crateMessage().isEmpty());
        LogManager.getLogger(this.getClass()).info(() -> "=== testCreateMessage: " + singletonMessageService.crateMessage());
    }

    @Test
    void testRunValue() {
        assertEquals(6660, singletonMessageService.runValue(10));
        LogManager.getLogger(this.getClass()).info(() -> "=== testRunValue: " + singletonMessageService.runValue(10));
    }
}
