package es.upm.miw.betca_injection;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SingletonMessageServiceTest {

    @Autowired
    private SingletonMessageService singletonMessageService;

    @Test
    void testCreateMessage() {
        assertFalse(singletonMessageService.crateMessage().isEmpty());
        LogManager.getLogger(this.getClass()).info(()->"=== message: " + singletonMessageService.crateMessage());
    }

    @Test
    void testCreateValue() {
        assertEquals(6660, singletonMessageService.createValue(10));
        LogManager.getLogger(this.getClass()).info(()->"=== value: " + singletonMessageService.createValue(10));
    }
}
