package functional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class CodeTest {

    @Test
    void testConsumer() {
        assertDoesNotThrow(() -> new Code().consumer());
    }

    @Test
    void testPredicate() {
        assertDoesNotThrow(() -> new Code().consumer());
    }

    @Test
    void testFunction() {
        assertDoesNotThrow(() -> new Code().consumer());
    }
}
