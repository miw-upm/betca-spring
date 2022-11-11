package functional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DecimalCollectionWithStreamTest {
    private DecimalCollectionWithStream decimalCollectionWithStream;

    @BeforeEach
    void before() {
        this.decimalCollectionWithStream = new DecimalCollectionWithStream();
        this.decimalCollectionWithStream.add(2.0);
        this.decimalCollectionWithStream.add(-1.0);
        this.decimalCollectionWithStream.add(3.0);
        this.decimalCollectionWithStream.add(2.0);
    }

    @Test
    void testDecimalCollection() {
        this.decimalCollectionWithStream = new DecimalCollectionWithStream();
        assertEquals(0, this.decimalCollectionWithStream.size());
    }

    @Test
    void testAdd() {
        assertEquals(4, this.decimalCollectionWithStream.size());
    }

    @Test
    void testSum() {
        assertEquals(6.0, this.decimalCollectionWithStream.sum(), 10e-5);
    }

    @Test
    void testSumEvenValues() {
        assertEquals(4.0, this.decimalCollectionWithStream.sumEvenValues(), 10e-5);
    }

    @Test
    void testSumIfEmpty() {
        assertEquals(Double.NaN, new DecimalCollectionWithStream().sum(), 10e-5);
    }

    @Test
    void testMax() {
        assertEquals(3.0, this.decimalCollectionWithStream.max(), 10e-5);
    }

    @Test
    void testMaxIfEmpty() {
        assertEquals(Double.NaN, new DecimalCollectionWithStream().max(), 10e-5);

    }
}

