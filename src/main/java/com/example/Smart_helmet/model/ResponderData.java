package com.example.Smart_helmet.model;

import java.time.Instant;

public class ResponderData {
    private double lat;
    private double lon;
    private String status;
    private Instant lastUpdated;

    public ResponderData() {
    }

    public ResponderData(double lat, double lon, String status, Instant lastUpdated) {
        this.lat = lat;
        this.lon = lon;
        this.status = status;
        this.lastUpdated = lastUpdated;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Instant lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return "ResponderData{" +
                "lat=" + lat +
                ", lon=" + lon +
                ", status=" + status +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
