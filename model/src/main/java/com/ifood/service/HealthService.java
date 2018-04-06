package com.ifood.service;

import com.ifood.model.ConnectionHealthHistory;

import java.util.List;

public interface HealthService {

    void receiveHealthSignal(String restaurantCode);

    ConnectionHealthHistory findHealthHistory(String restaurantCode);

    List<ConnectionHealthHistory> findHealthHistory(List<String> restaurantCodes);
}
