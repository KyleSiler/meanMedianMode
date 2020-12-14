package example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Calculates the Mean, Median, and Mode of a list of numbers.
 */
public class MeanMedianModeCalculator {
    private final List<Integer> numbers;
    private Double mean;
    private Double median;
    private List<Integer> mode;

    /**
     * Constructor for MeanMedianModeCalculator.
     *
     * @param numbers a non empty list of numbers
     */
    public MeanMedianModeCalculator(List<Integer> numbers) {
        assert (numbers != null);
        assert (numbers.size() > 0);

        // Calculating the median requires a sorted list. Sorting the list in the constructor to prevent
        numbers.sort(Integer::compareTo);

        this.numbers = numbers;
        calculateMean();
        calculateMedian();
        calculateMode();
    }

    /**
     * Calculates the mean of a list of numbers.
     *
     * <p>
     * The mean is the sum of all numbers divided by the count of numbers.
     * </p>
     */
    private void calculateMean() {
        double sum = numbers.stream().reduce((num, total) -> total += num).orElse(0);
        int count = numbers.size();

        mean = sum / count;
    }

    /**
     * Calculates the median of a list of numbers. Requires a sorted list.
     *
     * <p>
     * The median is calculated from a sorted list. For even sized lists, the two middle values are averaged. For odd sized lists, the middle value is returned.
     * </p>
     */
    private void calculateMedian() {

        if (numbers.size() % 2 == 0) {
            int middleIndex = (numbers.size() / 2) - 1;
            double sum = numbers.get(middleIndex) + numbers.get(middleIndex + 1);
            median = sum / 2;
        } else {
            int middleIndex = numbers.size() / 2;
            median = new Double(numbers.get(middleIndex));
        }
    }

    /**
     * Calculates the mode of a list of numbers.
     *
     * <p>
     * The mode is the number that appears most often. If there are multiple numbers with identical counts, then all numbers with the highest count will be returned.
     * </p>
     */
    private void calculateMode() {
        Map<Integer, Integer> numberToCount = new HashMap<>();

        int highestCount = 1;
        for (Integer num : numbers) {
            int curCount = numberToCount.getOrDefault(num, 0) + 1;

            if (highestCount < curCount) {
                highestCount = curCount;
            }
            numberToCount.put(num, curCount);
        }

        final int finalHighestCount = highestCount;
        mode = numberToCount.entrySet().stream().filter(entry -> entry.getValue().equals(finalHighestCount)).map(Map.Entry::getKey).collect(Collectors.toList());
    }

    /**
     * Gets the calculated results.
     *
     * @return a result object containing the mean, median, mode
     */
    public Result getResult() {
        return new Result(mean, median, mode);
    }
}
