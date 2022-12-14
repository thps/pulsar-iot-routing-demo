#!/bin/bash
gcloud beta container \
  --project "thorsig-pulsar-demos" \
  clusters create "gke-pulsar1" \
  --zone "us-central1-c" \
  --no-enable-basic-auth \
  --cluster-version "1.22.12-gke.300" \
  --release-channel "regular" \
  --machine-type "n1-standard-4" \
  --image-type "COS_CONTAINERD" \
  --disk-type "pd-ssd" \
  --disk-size "100" \
  --metadata disable-legacy-endpoints=true \
  --scopes "https://www.googleapis.com/auth/devstorage.read_only","https://www.googleapis.com/auth/logging.write","https://www.googleapis.com/auth/monitoring","https://www.googleapis.com/auth/servicecontrol","https://www.googleapis.com/auth/service.management.readonly","https://www.googleapis.com/auth/trace.append","default","https://www.googleapis.com/auth/ndev.clouddns.readwrite" \
  --max-pods-per-node "110" \
  --enable-autoscaling \
  --num-nodes "3" \
  --min-nodes "0" \
  --max-nodes "4" \
  --logging=SYSTEM,WORKLOAD \
  --monitoring=SYSTEM \
  --enable-ip-alias \
  --network "projects/thorsig-pulsar-demos/global/networks/default" \
  --subnetwork "projects/thorsig-pulsar-demos/regions/us-central1/subnetworks/default" \
  --no-enable-intra-node-visibility \
  --default-max-pods-per-node "110" \
  --no-enable-master-authorized-networks \
  --addons HorizontalPodAutoscaling,HttpLoadBalancing,GcePersistentDiskCsiDriver \
  --enable-autoupgrade \
  --enable-autorepair \
  --max-surge-upgrade 1 \
  --max-unavailable-upgrade 0 \
  --enable-shielded-nodes \
  --node-locations "us-central1-c" && \
  gcloud beta container \
  --project "thorsig-pulsar-demos" \
  node-pools create "function-worker" \
  --cluster "gke-pulsar1" \
  --zone "us-central1-c" \
  --machine-type "n1-standard-1" \
  --image-type "COS_CONTAINERD" \
  --disk-type "pd-standard" \
  --disk-size "100" \
  --metadata disable-legacy-endpoints=true \
  --scopes "https://www.googleapis.com/auth/devstorage.read_only","https://www.googleapis.com/auth/logging.write","https://www.googleapis.com/auth/monitoring","https://www.googleapis.com/auth/servicecontrol","https://www.googleapis.com/auth/service.management.readonly","https://www.googleapis.com/auth/trace.append","https://www.googleapis.com/auth/ndev.clouddns.readwrite" \
  --num-nodes "1" \
  --enable-autoscaling \
  --min-nodes "0" \
  --max-nodes "3" \
  --enable-autoupgrade \
  --enable-autorepair \
  --max-surge-upgrade 1 \
  --max-unavailable-upgrade 0 \
  --max-pods-per-node "110" \
  --node-locations "us-central1-c"
