package example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.GsonBuilder;

import java.util.List;

/**
 * Lambda handler.
 */
public class Handler implements RequestHandler<List<Integer>, String> {

    @Override
    public String handleRequest(List<Integer> event, Context context) {
        MeanMedianModeCalculator calculator = new MeanMedianModeCalculator(event);
        return new GsonBuilder().create().toJson(calculator.getResult());
    }
}