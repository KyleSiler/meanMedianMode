package example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.AWSXRayRecorderBuilder;
import com.amazonaws.xray.strategy.sampling.NoSamplingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Template provided by blank-java.
 */
class HandlerTest {

  public HandlerTest() {
    AWSXRayRecorderBuilder builder = AWSXRayRecorderBuilder.standard();
    builder.withSamplingStrategy(new NoSamplingStrategy());
    AWSXRay.setGlobalRecorder(builder.build());
  }

  @Test
  void invokeTest() {
    AWSXRay.beginSegment("test");
    List<Integer> event = Arrays.asList(1, 2, 3);
    Context context = new TestContext();
    Handler handler = new Handler();

    String response = handler.handleRequest(event, context);
    Result result = new GsonBuilder().create().fromJson(response, Result.class);

    assertEquals(2, result.getMean());
    assertEquals(2, result.getMedian());
    assertEquals(Arrays.asList(1, 2, 3), result.getMode());
    AWSXRay.endSegment();
  }
}
