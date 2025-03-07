package es.upm.api.miw.betca.injection;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class Service1WithoutInjectionTest {

    private Service1 service1;

    public Service1WithoutInjectionTest() {
        LogManager.getLogger(this.getClass()).info(() -> "----------------------   NEW CONTEXT ------------------------------ ");
    }

    @BeforeEach
    void before() {
        this.service1 = new Service1("test", 1);
    }

    @Test
    void testCreateMessage() {
        assertFalse(this.service1.crateMessage().isEmpty());
        LogManager.getLogger(this.getClass()).info(() -> "--- NOTE, without injection => message: " + this.service1.crateMessage());
    }

    @Test
    void testRunValue() {
        assertEquals(10, service1.runValue(10));
        LogManager.getLogger(this.getClass()).info(() -> "--- NOTE, without injection => value: " + service1.runValue(10));
    }
}
