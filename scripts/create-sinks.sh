#!/bin/bash
./bin/pulsar-admin sink create --sink-type cloud-storage --sink-config-file conf-storage-sink-audit.yaml
./bin/pulsar-admin sink create --sink-type cloud-storage --sink-config-file conf-storage-sink-prod.yaml
./bin/pulsar-admin sink create --sink-type cloud-storage --sink-config-file conf-storage-sink-dev.yaml
