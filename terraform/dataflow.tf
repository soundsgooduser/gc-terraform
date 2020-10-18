resource "google_dataflow_job" "my_dataflow_job" {
  depends_on = [
    google_storage_bucket.df_test_files]
  name = "my-dataflow-job"
  template_gcs_path = "gs://${google_storage_bucket.df_template_files.name}/templates/WordCountBatchDataFlow.json"
  temp_gcs_location = "gs://${google_storage_bucket.df_template_files.name}/tmp"
  parameters = {
    inputFile = "gs://${google_storage_bucket.df_test_files.name}/${google_storage_bucket_object.df_input_file.name}"
    output = "gs://${google_storage_bucket.df_test_files.name}/output.txt"
  }
  on_delete = "cancel"
}