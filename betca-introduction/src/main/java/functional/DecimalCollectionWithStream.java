package functional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DecimalCollectionWithStream {
    private List<Double> collection; // Error: Stream<Double>, un solo uso

    public DecimalCollectionWithStream() {
        this.collection = new ArrayList<>();
    }

    public void add(double value) {
        this.collection.add(value);
    }

    public long size() {
        return this.collection
                .size();
    }

    private double sum(Stream<Double> stream) {
        return stream
                .reduce(Double::sum)
                .orElse(Double.NaN);
    }

    public double sum() {
        return this.sum(
                this.collection.stream()
        );
    }

    public double sumEvenValues() {
        return this.sum(
                this.collection.stream()
                        .filter(value -> 0 == value % 2)
        );
    }

    public double max() {
        return this.collection.stream()
                .max(Double::compareTo)
                .orElse(Double.NaN);
    }

}
