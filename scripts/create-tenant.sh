#!/bin/bash
./bin/pulsar-admin tenants create iot-system
./bin/pulsar-admin namespaces create iot-system/ingest
./bin/pulsar-admin namespaces create iot-system/prod
./bin/pulsar-admin namespaces create iot-system/audit
./bin/pulsar-admin namespaces create iot-system/dev

