resource "google_storage_bucket" "df_template_files" {
  name = "df-template-files"
  force_destroy = true
  provisioner "local-exec" {
    when = create
    command = "cd .. ; mvn compile exec:java -Dexec.mainClass=com.google.cloud.dataflow.test.WordCountBatchDataFlow -Dexec.cleanupDaemonThreads=false -Dexec.args=\" --project=$GCLOUD_PROJECT --region=us-east1 --stagingLocation=gs://${google_storage_bucket.df_template_files.name}/staging --tempLocation=gs://${google_storage_bucket.df_template_files.name}/temp --templateLocation=gs://${google_storage_bucket.df_template_files.name}/templates/WordCountBatchDataFlow.json --runner=DataflowRunner\""
  }
}

resource "google_storage_bucket" "df_test_files" {
  depends_on = [
    google_storage_bucket.df_template_files]
  name = "df-test-files"
  force_destroy = true
}

resource "google_storage_bucket_object" "df_input_file" {
  depends_on = [
    google_storage_bucket.df_test_files]
  name = "df-input-file"
  source = "../files/df/input.txt"
  bucket = google_storage_bucket.df_test_files.name
}