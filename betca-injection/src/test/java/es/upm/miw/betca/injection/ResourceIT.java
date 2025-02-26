package es.upm.miw.betca.injection;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestConfig
class ResourceIT {

    @Autowired
    private Resource resource;

    @Test
    void testCreateMessage() {
        assertFalse(this.resource.createMessage().isEmpty());
        LogManager.getLogger(this.getClass()).info(() -> "===>>> message: " + this.resource.createMessage());
        this.resource.debug();
    }

    @Test
    void testCreateValue() {
        assertEquals(6660, this.resource.createValue());
        LogManager.getLogger(this.getClass()).info(() -> "===>>> value: " + this.resource.createValue());
        this.resource.debug();
    }
}
