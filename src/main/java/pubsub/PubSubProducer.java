package pubsub;

import com.google.api.core.ApiFuture;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PubsubMessage;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class PubSubProducer {

  private final String projectId;
  private final String topicId;

  public PubSubProducer() {
    projectId = System.getenv("PROJECT_ID");
    topicId = System.getenv("TOPIC_ID");
  }

  public PubSubProducer(String projectId, String topicId) {
    this.projectId = projectId;
    this.topicId = topicId;
  }

  public void publish(String message)
      throws IOException, ExecutionException, InterruptedException {
    ProjectTopicName topicName = ProjectTopicName.of(projectId, topicId);

    Publisher publisher = null;
    try {
      publisher = Publisher.newBuilder(topicName).build();

      ByteString data = ByteString.copyFromUtf8(message);
      PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();

      ApiFuture<String> messageIdFuture = publisher.publish(pubsubMessage);
      String messageId = messageIdFuture.get();
    } finally {
      if (publisher != null) {
        publisher.shutdown();
        publisher.awaitTermination(1, TimeUnit.MINUTES);
      }
    }
  }
}
