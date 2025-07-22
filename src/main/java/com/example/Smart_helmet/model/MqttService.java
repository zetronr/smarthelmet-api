package com.example.Smart_helmet.model;

import com.example.Smart_helmet.handler.MqttMessageHandler;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MqttService {

    private final String defaultBroker = "tcp://localhost:1883";
    private final String defaultTopic = "emergency/crash";
    private MqttClient client;
    private MqttMessageHandler handler;

    public void setMessageHandler(MqttMessageHandler handler) {
        this.handler = handler;
    }

    @PostConstruct
    public void init() {
        try {
            client = new MqttClient(defaultBroker, UUID.randomUUID().toString(), new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            client.connect(options);

            client.subscribe(defaultTopic, this::messageArrived);

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void messageArrived(String topic, MqttMessage message) {
        String payload = new String(message.getPayload());
        System.out.println("🔔 Received [" + topic + "]: " + payload);

        if (handler != null) {
            handler.handle(topic, payload);
        }
    }

    public void publish(String topic, String payload) {
        try {
            if (client != null && client.isConnected()) {
                MqttMessage message = new MqttMessage(payload.getBytes());
                message.setQos(1);
                client.publish(topic, message);
                System.out.println("Published to [" + topic + "]: " + payload);
            } else {
                System.err.println("MQTT client not connected.");
            }
        } catch (MqttException e) {
            System.err.println(" Error publishing: " + e.getMessage());
        }
    }

    public void subscribe(String topic, MqttMessageHandler customHandler) {
        try {
            client.subscribe(topic, (receivedTopic, message) -> {
                String payload = new String(message.getPayload());
                System.out.println("🔔 Received [" + receivedTopic + "]: " + payload);
                customHandler.handle(receivedTopic, payload);
            });
            System.out.println("Subscribed to topic: " + topic);
        } catch (MqttException e) {
            System.err.println("Error subscribing to topic: " + e.getMessage());
        }
    }

    @PreDestroy
    public void cleanup() {
        try {
            if (client != null && client.isConnected()) {
                client.disconnect();
                client.close();
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
