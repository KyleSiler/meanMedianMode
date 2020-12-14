package example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MeanMedianModeCalculatorTest {

    @Test
    public void nullListThrowsAssertionError() {
        assertThrows(AssertionError.class, () -> new MeanMedianModeCalculator(null));
    }

    @Test
    public void emptyListThrowsAssertionError() {
        assertThrows(AssertionError.class, () -> new MeanMedianModeCalculator(new ArrayList<>()));
    }

    @Test
    public void singleItemReturnedAsMeanMedianAndMode() {
        List<Integer> input = Arrays.asList(1);

        MeanMedianModeCalculator calculator = new MeanMedianModeCalculator(input);

        Result result = calculator.getResult();
        assertEquals(1, result.getMean());
        assertEquals(1, result.getMedian());
        assertEquals(Arrays.asList(1), result.getMode());
    }

    @Test
    public void oddSizeListCalculatesMeanMedianMode() {
        List<Integer> input = Arrays.asList(1,2,3);

        MeanMedianModeCalculator calculator = new MeanMedianModeCalculator(input);

        Result result = calculator.getResult();
        assertEquals(2, result.getMean());
        assertEquals(2, result.getMedian());
        assertEquals(Arrays.asList(1,2,3), result.getMode());
    }

    @Test
    public void evenSizeListCalculatesMedianFromTwoMiddleValues() {
        List<Integer> input = Arrays.asList(1,2,2,7);

        MeanMedianModeCalculator calculator = new MeanMedianModeCalculator(input);

        Result result = calculator.getResult();
        assertEquals(3, result.getMean());
        assertEquals(2, result.getMedian());
        assertEquals(Arrays.asList(2), result.getMode());
    }

    @Test
    public void listWithTwoNumbersThatHaveTheSameCountsAreBothReturnedInMode() {
        List<Integer> input = Arrays.asList(1,2,2,2,7,11,11,11);

        MeanMedianModeCalculator calculator = new MeanMedianModeCalculator(input);

        Result result = calculator.getResult();
        assertEquals(Arrays.asList(2,11), result.getMode());
    }
}