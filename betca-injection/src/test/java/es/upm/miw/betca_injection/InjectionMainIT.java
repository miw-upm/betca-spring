package es.upm.miw.betca_injection;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class InjectionMainIT {

    @Autowired
    private InjectionMain injectionMain;

    @Test
    void testCreateMessage() {
        assertTrue(this.injectionMain.createMessage().length() > 0);
        LogManager.getLogger(this.getClass()).info("===>>> message: " + this.injectionMain.createMessage());
        this.injectionMain.debug();
    }

    @Test
    void testCreateValue() {
        assertEquals(6660, this.injectionMain.createValue());
        LogManager.getLogger(this.getClass()).info("===>>> value: " + this.injectionMain.createValue());
        this.injectionMain.debug();
    }
}
