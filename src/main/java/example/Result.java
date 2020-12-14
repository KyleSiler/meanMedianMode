package example;

import java.util.Collections;
import java.util.List;

/**
 * Class to handle result of calculations.
 */
public class Result {
    private final Double mean;
    private final Double median;
    private final List<Integer> mode;

    public Result(Double mean, Double median, List<Integer> mode) {
        this.mean = mean;
        this.median = median;
        this.mode = Collections.unmodifiableList(mode);
    }

    public Double getMean() {
        return mean;
    }

    public Double getMedian() {
        return median;
    }

    public List<Integer> getMode() {
        return mode;
    }
}
