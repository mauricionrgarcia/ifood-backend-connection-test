package com.ifood.service;

import com.ifood.model.ConnectionHealthHistory;

import java.time.LocalDateTime;
import java.util.List;

public interface HealthService {

    void receiveHealthSignal(String restaurantCode);

    List<ConnectionHealthHistory> findHealthHistory(String restaurantCode, LocalDateTime startDate, LocalDateTime endDate);

    List<ConnectionHealthHistory> findHealthHistory(List<String> restaurantCodes, LocalDateTime startDate, LocalDateTime endDate);
}
