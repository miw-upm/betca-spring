package es.upm.miw.betca_injection;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SingletonMessageServiceWithoutInjectionTest {
    private SingletonMessageService singletonMessageService;

    @BeforeEach
    void before() {
        this.singletonMessageService = new SingletonMessageService("test",1);
    }

    @Test
    void testCreateMessage() {
        assertTrue(this.singletonMessageService.crateMessage().length() > 0);
        LogManager.getLogger(this.getClass()).info("--- NOTE, without injection => message: " + this.singletonMessageService.crateMessage());
    }
    @Test
    void testCreateValue() {
        assertEquals(10, singletonMessageService.createValue(10));
        LogManager.getLogger(this.getClass()).info("--- NOTE, without injection => value: " + singletonMessageService.createValue(10));
    }
}
