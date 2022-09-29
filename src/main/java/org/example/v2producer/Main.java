package org.example.v2producer;

import org.apache.pulsar.client.api.*;
import org.apache.pulsar.client.impl.schema.JSONSchema;
import org.example.DeviceMessage;

import java.util.Map;
import java.util.Set;
import java.time.Instant;

/*
 * This code represents a V2 IoT Device Producer
 * This is a mockup to show how data is generated
 * by an edge device that would speak to an API
 * gateway that authorizes developers and testers
 * so they can set environment headers to be part
 * of non-production data flows. The gateway would
 * set message properties for ingested messages based
 * on this. IMPORTANT to not just trust user input.
 */

public class Main {
    public static void main(String[] args) throws PulsarClientException {
        System.out.println("Starting V2 Producer App");

        String ingestTopic = "persistent://iot-system/ingest/commands";
        String pulsarServiceUrl = "pulsar://pulsar.luna.tho.rs:6650";

        PulsarClient client = PulsarClient.builder()
                .serviceUrl(pulsarServiceUrl)
                .build();

        Producer<DeviceMessage> deviceMessageProducer = client.newProducer(JSONSchema.of(DeviceMessage.class))
                .topic(ingestTopic)
                .create();

        DeviceMessage deviceMessaage;
        while (true) {
            try {
                Thread.sleep((long)(Math.random() * 1000));  // Simple throttle mechanism for POC Demo Purposes
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // The IoT Device's "PING" command is implemented here for demonstration purposes, improvement from NOOP in V1
            deviceMessaage = new DeviceMessage("device-02-dev", "ping", Instant.now().getEpochSecond());
            deviceMessageProducer.newMessage()
                    .key(java.util.UUID.randomUUID().toString())
                    .value(deviceMessaage)
                    .property("app","service-dev-thor")
                    .property("env","dev")
                    .send();

        }
    }

}