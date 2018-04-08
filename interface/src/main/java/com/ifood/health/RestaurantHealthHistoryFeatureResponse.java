package com.ifood.health;

import com.ifood.domain.ConnectionHealthSignal;

import java.util.List;

public class RestaurantHealthHistoryFeatureResponse {

    private String code;

    private List<ConnectionHealthSignal> connections;

    public RestaurantHealthHistoryFeatureResponse(String code, List<ConnectionHealthSignal> connectionHealthHistories) {
        this.code = code;
        this.connections = connectionHealthHistories;
    }

    public String getCode() {
        return code;
    }

    public List<ConnectionHealthSignal> getConnections() {
        return connections;
    }
}
