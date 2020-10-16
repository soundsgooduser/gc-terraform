### Steps starting to work with GCP:
* create google cloud free account
* create github project
* install terraform locally
* watch hands on lab "Terraform Basics: Understanding init, plan, and apply"
* install google-cloud-sdk locally: brew cask install google-cloud-sdk
* create service account manually using google console.
  we need it to allow terraform to execute api calls against google cloud
* create some folder where credentials will be kept: /Users/my_user_name/gc/credentials/
* execute command: gcloud auth login
* execute command: gcloud config set project <put here google cloud project name>
* execute command: gcloud iam service-accounts keys create /Users/my_user_name/gc/credentials/credentials.json --iam-account <put here email of created service account>
* add to .bash_profile on mac: export GOOGLE_APPLICATION_CREDENTIALS="/Users/my_user_name/gc/credentials/credentials.json"
* add to .bash_profile on mac: export GCLOUD_PROJECT=<put here project id from google console>
* create main.tf file in github project adding code:
```
  provider "google" {
    region = "us-central1"
    zone = "us-central1-c"
  }

  resource "google_compute_network" "vpc_network" {
    name = "terraform-network"
    auto_create_subnetworks = "true"
  }
```

* execute command: terraform init
* execute command: terraform plan
* execute command: terraform apply
* verify that resource has been created on google cloud
* execute command: terraform destroy
* verify that resource has been deleted on google cloud
* helpful docs:
    * https://cloud.google.com/functions/docs/env-var