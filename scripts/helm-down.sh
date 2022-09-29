#!/bin/bash
# helm install pulsar datastax-pulsar/pulsar --namespace pulsar --values values.yaml --create-namespace
helm uninstall pulsar --namespace pulsar

