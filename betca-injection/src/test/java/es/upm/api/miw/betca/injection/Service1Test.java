package es.upm.api.miw.betca.injection;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestConfig
class Service1Test {

    @Autowired
    private Service1 singletonService1;

    @Test
    void testCreateMessage() {
        assertFalse(singletonService1.crateMessage().isEmpty());
        LogManager.getLogger(this.getClass()).info(() -> "=== testCreateMessage: " + singletonService1.crateMessage());
    }

    @Test
    void testRunValue() {
        assertEquals(6660, singletonService1.runValue(10));
        LogManager.getLogger(this.getClass()).info(() -> "=== testRunValue: " + singletonService1.runValue(10));
    }
}
