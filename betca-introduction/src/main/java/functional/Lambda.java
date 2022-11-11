package functional;

import org.apache.logging.log4j.LogManager;

import java.util.function.*;


public class Lambda {

    public static final Consumer<String> logInfo = // accept(T)
            LogManager.getLogger(Lambda.class)::info;  // item ->: implicit
    public static final Consumer<String> logInfoDetail = // accept(T)
            msg -> LogManager.getLogger(Lambda.class).info("Consumer: {}", msg);
    public static final Function<String, Integer> convertToInt = // apply(T):R
            Integer::parseInt; // item -> & return: implicit
    public static final Function<String, Integer> convertToIntPlus1 = // apply(T):R
            value -> Integer.parseInt(value) + 1; //return: implicit
    public static final Function<String, Integer> convertToIntZeroTo1 = // apply(T):R
            value -> {
                if ("0".equals(value)) {
                    return 1;
                } else {
                    return Integer.parseInt(value);
                }
            };
    public static final Predicate<String> equalsTo2 = // test(T):boolean
            "two"::equals; //return: implicit
    public static final Supplier<String> generateDots = // get(): T
            () -> "..."; //return: implicit
    public static final BiConsumer<String, String> biLogInfo =  // apply(T,U):R
            (msg1, msg2) -> LogManager.getLogger(Lambda.class).info("Bi-Consumer: {}, {}", msg1, msg2);
    public static final BiFunction<Integer, Integer, Integer> multiply =  // accept(T,U):R
            (Integer x, Integer y) -> x * y; //return: implicit
    public static final BiPredicate<String, String> compare =  // apply(T,U):R
            String::equals; //return: implicit

    private Lambda() {
        throw new IllegalStateException("Utility class");
    }
}
