#!/bin/bash
./bin/pulsar-admin functions create --classname org.example.PropFilterFunction --jar PropRouterFunction-1.3.5-SNAPSHOT.jar --inputs persistent://iot-system/ingest/commands --output persistent://iot-system/audit/commands --tenant iot-system --namespace ingest --name PropRouterFunction
