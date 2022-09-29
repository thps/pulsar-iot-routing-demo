#!/bin/bash
gcloud container clusters delete "gke-pulsar1" \
  --project "thorsig-pulsar-demos" \
  --zone "us-central1-c"

