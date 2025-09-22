package es.upm.api.miw.betca.injection;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ContextConfiguration(classes = Application.class)
@SpringBootTest
@ActiveProfiles("test")
class Resource1IT {

    @Autowired
    private Resource1 resource1;

    @Test
    void testCreateMessage() {
        assertFalse(this.resource1.createMessage().isEmpty());
        LogManager.getLogger(this.getClass()).info(() -> "===>>> message: " + this.resource1.createMessage());
        this.resource1.debug();
    }

    @Test
    void testCreateValue() {
        assertEquals(6660, this.resource1.createValue());
        LogManager.getLogger(this.getClass()).info(() -> "===>>> value: " + this.resource1.createValue());
        this.resource1.debug();
    }
}
