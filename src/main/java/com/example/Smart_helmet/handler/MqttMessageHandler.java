package com.example.Smart_helmet.handler;

public interface MqttMessageHandler {
    void handle(String topic, String payload);
}
