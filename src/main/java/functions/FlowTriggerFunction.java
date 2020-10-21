package functions;

import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.logging.Logger;
import pubsub.PubSubProducer;

public class FlowTriggerFunction implements HttpFunction {

  private static final Logger logger = Logger.getLogger(FlowTriggerFunction.class.getName());
  private static final Gson gson = new Gson();

  @Override
  public void service(HttpRequest request, HttpResponse response) {
    logger.info("trigger flow");
    try {
      JsonObject body = gson.fromJson(request.getReader(), JsonObject.class);
      logger.info("trigger flow request " + body.toString());
      final PubSubProducer producer = new PubSubProducer();
      producer.publish(body.toString());
    } catch (Exception ex) {
      logger.severe("error " + ex.getMessage());
    }
  }
}