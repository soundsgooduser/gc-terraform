resource "google_cloudfunctions_function" "flow_trigger_function" {
  name = "flow-trigger-function"
  available_memory_mb = 256
  source_archive_bucket = "${google_storage_bucket.functions_code_storage.name}"
  source_archive_object = "${google_storage_bucket_object.functions_archive.name}"
  trigger_http = true
  timeout = 60
  runtime = "java11"
  entry_point = "functions.FlowTriggerFunction"
  environment_variables = {
    PROJECT_ID = "${var.project_id}"
    TOPIC_ID = "${google_pubsub_topic.flow_trigger_pubsub_topic.name}"
  }
}