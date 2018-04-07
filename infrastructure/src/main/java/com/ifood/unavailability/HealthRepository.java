package com.ifood.unavailability;

import com.ifood.entity.ConnectionHistoryEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface HealthRepository {

    void insertSignalRegistry(ConnectionHistoryEntity connectionHistoryEntity);

    List<ConnectionHistoryEntity> findHealthHistory(String restaurantCode, LocalDateTime startDate, LocalDateTime endDate);

    List<ConnectionHistoryEntity> findHealthHistory(List<String> restaurantCode, LocalDateTime startDate, LocalDateTime endDate);
}
