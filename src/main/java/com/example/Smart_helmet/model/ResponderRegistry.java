package com.example.Smart_helmet.model;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ResponderRegistry {
    private final Map<String, ResponderData> responderMap = new ConcurrentHashMap<>();

    public void updateResponder(String id, ResponderData data) {
        responderMap.put(id, data);
    }

    public ResponderData getResponder(String id) {
        return responderMap.get(id);
    }

    public Map<String, ResponderData> getAllResponders() {
        return responderMap;
    }



}
