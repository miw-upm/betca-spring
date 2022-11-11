package functional;

import org.junit.jupiter.api.Test;

public class CodeTest {

    @Test
    void testConsumer() {
        new Code().consumer();
    }

    @Test
    void testPredicate() {
        new Code().predicate();
    }

    @Test
    void testFunction() {
        new Code().function();
    }
}
