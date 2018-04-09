package com.ifood.service;

import com.ifood.domain.ConnectionHealthSignal;
import com.ifood.domain.RestaurantAvailability;

import java.time.LocalDateTime;
import java.util.List;

public interface HealthService {

    void receiveHealthSignal(String restaurantCode);

    List<ConnectionHealthSignal> findHealthHistory(String restaurantCode, LocalDateTime startDate, LocalDateTime endDate);

    List<ConnectionHealth> checkRestaurantsConnection(List<String> restaurantCodes);
}
