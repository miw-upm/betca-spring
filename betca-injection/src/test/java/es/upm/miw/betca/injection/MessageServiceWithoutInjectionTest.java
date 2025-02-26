package es.upm.miw.betca.injection;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class MessageServiceWithoutInjectionTest {

    private MessageService messageService;

    public MessageServiceWithoutInjectionTest() {
        LogManager.getLogger(this.getClass()).info(() -> "----------------------   NEW CONTEXT ------------------------------ ");
    }

    @BeforeEach
    void before() {
        this.messageService = new MessageService("test", 1);
    }

    @Test
    void testCreateMessage() {
        assertFalse(this.messageService.crateMessage().isEmpty());
        LogManager.getLogger(this.getClass()).info(() -> "--- NOTE, without injection => message: " + this.messageService.crateMessage());
    }

    @Test
    void testRunValue() {
        assertEquals(10, messageService.runValue(10));
        LogManager.getLogger(this.getClass()).info(() -> "--- NOTE, without injection => value: " + messageService.runValue(10));
    }
}
