package com.example.Smart_helmet.model;

import com.example.Smart_helmet.handler.MqttMessageHandler;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ResponderListener implements MqttMessageHandler {
    private final MqttService mqttService;
    private final ConcurrentHashMap<String, ResponderData> responders = new ConcurrentHashMap<>();
    private final ResponderRegistry registry;
    public ResponderListener(MqttService mqttService, ResponderRegistry registry) {
        this.mqttService = mqttService;
        this.registry = registry;
    }


    @PostConstruct
    public void init(){
        mqttService.subscribe("responder/location/#", this);
    }

    @Override
    public void handle(String topic, String payload) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(payload);

            String deviceId = root.get("id").asText();
            double lat = root.get("lat").asDouble();
            double lon = root.get("lon").asDouble();
            String statusStr = root.get("status").asText();
            String status = String.valueOf(statusStr.toUpperCase());

            ResponderData data = new ResponderData(lat, lon, status, Instant.now());
            responders.put(deviceId, data);
            registry.updateResponder(deviceId, data);
            System.out.println("Updated Responder [" + deviceId + "] -> " + data);
        } catch (Exception e) {
            System.err.println("Error parsing location payload: " + e.getMessage());
        }
    }     public ResponderData getResponderData(String deviceId) {
        return responders.get(deviceId);
    }

    public ConcurrentHashMap<String, ResponderData> getAllResponders() {
        return responders;
    }
}
