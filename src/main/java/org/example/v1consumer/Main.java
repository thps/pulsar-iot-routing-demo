package org.example.v1consumer;

import org.apache.pulsar.client.api.*;

import java.util.Map;
import java.util.Set;

/*
* This code represents a V1 IoT Service Consumer
* As you can see, the messages are being consumed
* but not much is being done with them. V2 version
* begins to process messages. In the current scenario
* the POC shows that code being in non-prod data flow.
*/

public class Main {
    public static void main(String[] args) throws PulsarClientException {
        System.out.println("Starting V1 Consumer App");

        String SUBSCRIPTION_NAME = "v1consumer-shared-subscription";
        String ingestTopic = "persistent://iot-system/prod/commands";
        //TODO: secure if not simple demo
        String pulsarServiceUrl = "pulsar://pulsar.luna.tho.rs:6650";

        PulsarClient client = PulsarClient.builder()
                .serviceUrl(pulsarServiceUrl)
                .build();

        Consumer consumer = client.newConsumer()
                .topic(ingestTopic)
                .subscriptionType(SubscriptionType.Shared)
                .subscriptionName(SUBSCRIPTION_NAME)
                .subscribe();


        while (true) {
            // Wait for a message
            Message msg = consumer.receive();

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

                // Acknowledge the message so that it can be deleted by the message broker
                consumer.acknowledge(msg);
            } catch (Exception e) {
                // Message failed to process, redeliver later
                consumer.negativeAcknowledge(msg);
            }
        }

    }
}