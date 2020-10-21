resource "google_storage_bucket" "functions_code_storage" {
  name = "functions-code-storage"
}

resource "google_storage_bucket_object" "functions_archive" {
  name = "functions-archive"
  bucket = "${google_storage_bucket.functions_code_storage.name}"
  source = "../target/functions-1.0.0.zip"
}