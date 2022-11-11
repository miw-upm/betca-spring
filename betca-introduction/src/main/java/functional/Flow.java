package functional;

import org.apache.logging.log4j.LogManager;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Flow {

    public Stream<String> streamFromList() {
        return Arrays.asList("0", "1", "2").stream();
    }

    public IntStream streamFromRange() {
        return IntStream.range(0, 4);
    }

    public Stream<String> streamFromOf() {
        return Stream.of("0", "1", "2");
    }

    public Stream<Integer> streamFromGenerate(long limit) {
        return Stream
                .generate(new Random()::nextInt)
                .limit(limit);
    }

    public Stream<Integer> streamFromIterate(long limit) {
        return Stream
                .iterate(0, n -> n + 2)
                .limit(limit);
    }

    public List<Integer> toList(Stream<Integer> stream) {
        return stream.collect(Collectors.toList());
    }

    public Integer[] toArray(Stream<Integer> stream) {
        return stream.toArray(Integer[]::new);
    }

    public Stream<Integer> filterPositives(Stream<Integer> stream) {
        return stream.filter(value -> value >= 0);
    }

    public Stream<Integer> skipFirst(Stream<Integer> stream) {
        return stream.skip(1);
    }

    public long size(Stream<Integer> stream) {
        return stream.count();
    }

    public Stream<Integer> removeCopy(Stream<Integer> stream) {
        return stream.distinct();
    }

    public Stream<String> debug(Stream<String> stream) {
        return stream.peek(LogManager.getLogger(this.getClass())::debug);
    }

    public Stream<String> mapToString(Stream<Integer> stream) {
        return stream.map(String::valueOf);
    }

    public Stream<Integer> increment(Stream<Integer> stream) {
        return stream.map(value -> value + 1);
    }

    public Stream<Integer> flatten(Stream<Integer[]> stream) {
        return stream.flatMap(Stream::of); //sin flatmap Stream<Stream>
    }

    public double sum(Stream<Integer> stream) {  //overage
        return stream
                .reduce(Integer::sum)// Optional<Double>
                .orElseThrow(ArithmeticException::new);
    }

    public double max(Stream<Integer> stream) {
        return stream
                .max(Integer::compareTo) // Optional<Double>
                .orElseThrow(ArithmeticException::new);
    }

    public double mul(Stream<Integer> stream) {
        return stream.reduce(1, (accumulator, value) -> accumulator * value);
    }

    public double incrementAndSum(Stream<Integer> stream) {
        return stream
                .map(value -> value + 1)
                .mapToDouble(Double::valueOf)
                .reduce(Double::sum)
                .orElseThrow(ArithmeticException::new);
    }

    public void terminal(Stream<Integer> stream) {
        stream.forEach(LogManager.getLogger(this.getClass())::info);
    }

    public boolean isValueContent(Stream<Integer> stream, Integer value) {
        return stream.anyMatch(value::equals);
    }

    public boolean areAllPositive(Stream<Integer> stream) {
        return stream.allMatch(value -> value >= 0);
    }

}
