package com.example.Smart_helmet.model;

import jakarta.validation.constraints.Null;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HelpAssignService {
    private final ResponderRegistry registry;

    public HelpAssignService(ResponderRegistry registry) {
        this.registry = registry;
    }

    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a =
                Math.sin(dLat/2) * Math.sin(dLat/2) +
                        Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                                Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return R * c;
    }

    public String findNearesthelp(double lat ,double lon){
        var all = registry.getAllResponders();

        String nearestId = null;
        double minDist = Double.MAX_VALUE;

        for (Map.Entry<String, ResponderData> entry : all.entrySet()) {
            String id = entry.getKey();
            ResponderData data = entry.getValue();

            double dist = distance(lat, lon, data.getLat(), data.getLon());
            if (dist < minDist) {
                minDist = dist;
                nearestId = id;
            }
        }

        return nearestId;
    }

}
