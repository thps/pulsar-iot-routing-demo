package org.example.v2consumer;

import org.apache.pulsar.client.api.*;

import java.util.Map;
import java.util.Set;

import org.apache.pulsar.client.impl.schema.JSONSchema;
import org.example.DeviceMessage;

/*
 * This code represents a V2 IoT Service Consumer
 * As you can see, a developer might be starting to
 * implement message processing from the IoT device.
 * We just show message consumption and the ability
 * for this code to consume from non-production data
 * flow. See project README on more details for why.
 */


public class Main {
    public static void main(String[] args) throws PulsarClientException {
        System.out.println("Starting V2 Consumer App");

        String SUBSCRIPTION_NAME = "v2consumer-shared-subscription";
        String ingestTopic = "persistent://iot-system/dev/commands";
        //TODO: secure if not simple demo
        String pulsarServiceUrl = "pulsar://pulsar.luna.tho.rs:6650";

        PulsarClient client = PulsarClient.builder()
                .serviceUrl(pulsarServiceUrl)
                .build();

        Consumer<DeviceMessage> deviceMessageConsumer = client.newConsumer(JSONSchema.of(DeviceMessage.class))
                .topic(ingestTopic)
                .subscriptionType(SubscriptionType.Shared)
                .subscriptionName(SUBSCRIPTION_NAME)
                .subscribe();


        while (true) {
            // Wait for a message
            Message<DeviceMessage> msg = deviceMessageConsumer.receive();

            try {
                // Do something with the message
                System.out.println("Message received: " + new String(msg.getData()));

                Map<String,String> props = msg.getProperties();
                Set<String> keys = props.keySet();
                if (keys.contains("env") ){
                    System.out.println("  Message has env property: " + new String(props.get("env")));
                } else {
                    System.out.println("  Message does not have env property.");
                }

                DeviceMessage deviceMessage = msg.getValue();
                if ( "ping".equals(deviceMessage.getPayload()) ) {
                    System.out.println("  Got PING command payload.");
                    //TODO: Implement PING Command outside of demo scope
                };
                if ( "noop".equals(deviceMessage.getPayload()) ) {
                    System.out.println("  Got NOOP command payload.");
                    //TODO: Implement NOOP Command outside of demo scope
                };
                // Acknowledge the message so that it can be deleted by the message broker
                deviceMessageConsumer.acknowledge(msg);
            } catch (Exception e) {
                // Message failed to process, redeliver later
                deviceMessageConsumer.negativeAcknowledge(msg);
            }

        }

    }
}