package functional;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LambdaTest {

    @Test
    void testConsumers() {
        assertDoesNotThrow(() -> Stream.of("one", "two", "three").forEach(Lambda.logInfo));
        assertDoesNotThrow(() -> Stream.of("one", "two", "three").forEach(Lambda.logInfoDetail));
    }

    @Test
    void testFunctionConvertToInt() {
        List<Integer> list = Stream.of("1", "2", "3").map(Lambda.convertToInt).collect(Collectors.toList());
        assertEquals(Arrays.asList(1, 2, 3), list);
    }

    @Test
    void testFunctionConvertToPlus1() {
        List<Integer> list = Stream.of("1", "2", "3").map(Lambda.convertToIntPlus1).collect(Collectors.toList());
        assertEquals(Arrays.asList(2, 3, 4), list);
    }

    @Test
    void testFunctionConvertToIntZeroTo1() {
        List<Integer> list = Stream.of("0", "1", "2").map(Lambda.convertToIntZeroTo1).collect(Collectors.toList());
        assertEquals(Arrays.asList(1, 1, 2), list);
    }

    @Test
    void testPredicate() {
        List<String> list = Stream.of("one", "two", "three", "two").filter(Lambda.equalsTo2).collect(Collectors.toList());
        assertEquals(Arrays.asList("two", "two"), list);
    }

    @Test
    void testSupplier() {
        List<String> list = Stream.generate(Lambda.generateDots).limit(3).collect(Collectors.toList());
        assertEquals(Arrays.asList("...", "...", "..."), list);
    }

}

