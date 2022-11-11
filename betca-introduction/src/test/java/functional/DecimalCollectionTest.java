package functional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DecimalCollectionTest {
    private DecimalCollection decimalCollection;

    @BeforeEach
    void before() {
        this.decimalCollection = new DecimalCollection();
        this.decimalCollection.add(2.0);
        this.decimalCollection.add(-1.0);
        this.decimalCollection.add(3.0);
        this.decimalCollection.add(2.0);
    }

    @Test
    void testDecimalCollection() {
        this.decimalCollection = new DecimalCollection();
        assertEquals(0, this.decimalCollection.size());
    }

    @Test
    void testAdd() {
        assertEquals(4, this.decimalCollection.size());
    }

    @Test
    void testSum() {
        assertEquals(6.0, this.decimalCollection.sum(), 10e-5);
    }

    @Test
    void testSumEvenValues() {
        assertEquals(4.0, this.decimalCollection.sumEvenValues(), 10e-5);
    }

    @Test
    void testSumIfEmpty() {
        assertEquals(Double.NaN, new DecimalCollection().sum(), 10e-5);
    }

    @Test
    void testMax() {
        assertEquals(3.0, this.decimalCollection.max(), 10e-5);
    }

    @Test
    void testMaxIfEmpty() {
        assertEquals(Double.NaN, new DecimalCollection().max(), 10e-5);
    }

}
