package com.ifood.health;

import com.ifood.model.ConnectionHealthHistory;

import java.util.List;

public class RestaurantHealthHistoryFeatureResponse {

    private String code;

    private List<ConnectionHealthHistory> connections;

    public RestaurantHealthHistoryFeatureResponse(String code, List<ConnectionHealthHistory> connectionHealthHistories) {
        this.code = code;
        this.connections = connectionHealthHistories;
    }

    public String getCode() {
        return code;
    }

    public List<ConnectionHealthHistory> getConnections() {
        return connections;
    }
}
