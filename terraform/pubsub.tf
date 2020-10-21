resource "google_pubsub_topic" "flow_trigger_pubsub_topic" {
  name = "flow-trigger-pubsub-topic"
}

resource "google_pubsub_subscription" "flow_trigger_pubsub_subscription" {
  name = "flow-trigger-pubsub-subscription"
  topic = google_pubsub_topic.flow_trigger_pubsub_topic.name
  ack_deadline_seconds = 20
}