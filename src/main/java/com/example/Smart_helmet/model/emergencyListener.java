package com.example.Smart_helmet.model;

import com.example.Smart_helmet.handler.MqttMessageHandler;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class emergencyListener implements MqttMessageHandler {

    private final MqttService mqttService;
    private  final HelpAssignService help;
    public emergencyListener(MqttService mqttService, HelpAssignService help) {
        this.mqttService = mqttService;
        this.help = help;
    }

    @PostConstruct
    public void init() {
        mqttService.setMessageHandler(this);
        System.out.println("emergencyListener registered as message handler");
    }

    @Autowired
    UserAuthservice UserService;
    @Override
    public void handle(String topic, String payload) {
        System.out.println("emergencyListener received: " + payload );

        try {

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(payload);

            String deviceId = root.get("id").asText();
            double lat = root.get("lat").asDouble();
            double lon = root.get("lon").asDouble();
            int bpm = root.get("bpm").asInt();
            UserInfo user = UserService.getUserByDeviceId(deviceId);
            Instant receivedTime = Instant.now();
            System.out.println("Device ID: " + deviceId);
            System.out.println("Location: (" + lat + ", " + lon + ")");
            System.out.println("BPM: " + bpm);
            System.out.println("Time: " + receivedTime);
            String payload_msg = "Fullname: " + user.getFullname() + ", age: " + user.getDateofbirth()+", lat: "+lat +", lon: "+lon;

            String ResponderId = help.findNearesthelp(lat,lon);
            System.out.println(ResponderId);
            mqttService.publish("crash/alert/"+ResponderId, payload_msg);


        }catch (Exception e) {
            System.err.println("Error parsing payload: " + e.getMessage());
        }

    }
}
