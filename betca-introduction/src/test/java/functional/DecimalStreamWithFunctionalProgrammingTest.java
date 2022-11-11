package functional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DecimalStreamWithFunctionalProgrammingTest {

    private Stream<Double> stream;

    @BeforeEach
    void before() {
        this.stream = Stream.of(2.0, -1.0, 3.0, 2.0);
    }

    @Test
    void testSize() {
        assertEquals(4, new DecimalStreamWithFunctionalProgramming().size(this.stream));
    }

    @Test
    void testSum() {
        assertEquals(6.0, new DecimalStreamWithFunctionalProgramming().sum(this.stream), 10e-5);
    }

    @Test
    void testSumIfEmpty() {
        assertEquals(Double.NaN, new DecimalStreamWithFunctionalProgramming().sum(Stream.empty()), 10e-5);
    }

    @Test
    void testSumEvenValues() {
        assertEquals(4.0, new DecimalStreamWithFunctionalProgramming().sumEvenValues(this.stream), 10e-5);
    }

    @Test
    void testMax() {
        assertEquals(3.0, new DecimalStreamWithFunctionalProgramming().max(this.stream), 10e-5);
    }

    @Test
    void testMaxIfEmpty() {
        assertEquals(Double.NaN, new DecimalStreamWithFunctionalProgramming().max(Stream.empty()), 10e-5);
    }
}
