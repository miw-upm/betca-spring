package es.upm.miw.persistence.jpa.entities;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class AnyEntityTest {

    @Test
    public void testEquals() {
        assertFalse(new AnyEntity().equals(null));
    }
}