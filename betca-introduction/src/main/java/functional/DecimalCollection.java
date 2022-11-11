package functional;

import java.util.ArrayList;
import java.util.List;

public class DecimalCollection {
    private List<Double> collection;

    public DecimalCollection() {
        this.collection = new ArrayList<>();
    }

    public void add(double value) {
        this.collection.add(value);
    }

    public int size() {
        return this.collection.size();
    }

    public double sum() {
        double sum = Double.NaN;
        for (double item : this.collection) {
            sum = doSum(sum, item);
        }
        return sum;
    }

    private double doSum(double sum, double item) {
        if (Double.isNaN(sum)) {
            sum = item;
        } else {
            sum += item;
        }
        return sum;
    }

    public double sumEvenValues() {
        double sum = Double.NaN;
        for (double item : this.collection) {
            if (0 == item % 2) {
                sum = doSum(sum, item);
            }
        }
        return sum;
    }

    public double max() {
        double max = Double.NaN;
        for (double item : this.collection) {
            if (Double.isNaN(max) || item > max) {
                max = item;
            }
        }
        return max;
    }

}
