provider "google" {
  region = "us-central1"
  zone = "us-central1-c"
}

resource "google_compute_network" "vpc_network" {
  name = "terraform-network"
  auto_create_subnetworks = "true"
}