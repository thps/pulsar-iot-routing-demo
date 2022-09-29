#!/bin/bash
./bin/pulsar-admin sink delete --tenant iot-system --namespace audit --name cloud-storage-sink
./bin/pulsar-admin sink delete --tenant iot-system --namespace prod  --name cloud-storage-sink
./bin/pulsar-admin sink delete --tenant iot-system --namespace dev   --name cloud-storage-sink

