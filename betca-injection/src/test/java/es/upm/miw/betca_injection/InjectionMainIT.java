package es.upm.miw.betca_injection;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class InjectionMainIT {

    @Autowired
    private InjectionMain injectionMain;

    @Test
    void testGetMessage() {
        assertTrue(this.injectionMain.getMessage().length() > 0);
        LogManager.getLogger(this.getClass()).info("===>>> message: " + this.injectionMain.getMessage());
        this.injectionMain.debug();
    }

    @Test
    void testGetValue() {
        assertEquals(100, this.injectionMain.getValue());
        LogManager.getLogger(this.getClass()).info("===>>> value: " + this.injectionMain.getValue());
        this.injectionMain.debug();
    }
}
