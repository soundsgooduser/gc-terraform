package it;

import pubsub.PubSubProducer;

public class PubSubProducerIT {

  public static void main(String... args) throws Exception {
    String projectId = "focus-ensign-292616";
    String topicId = "test";

    final PubSubProducer producer = new PubSubProducer(projectId, topicId);
    producer.publish("my test message first");
  }
}
