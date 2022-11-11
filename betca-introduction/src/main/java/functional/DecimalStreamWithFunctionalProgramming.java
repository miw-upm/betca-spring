package functional;

import java.util.stream.Stream;

public class DecimalStreamWithFunctionalProgramming {

    public long size(Stream<Double> stream) {
        return stream
                .count();
    }

    public double sumEvenValues(Stream<Double> stream) {
        return this.sum(
                stream.filter(value -> 0 == value % 2)
        );
    }

    public double sum(Stream<Double> stream) {
        return stream
                .reduce(Double::sum) // Optional<Double>
                .orElse(Double.NaN);
    }

    public double max(Stream<Double> stream) {
        return stream
                .max(Double::compareTo) // Optional<Double>
                .orElse(Double.NaN);
    }

}
