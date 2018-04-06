package com.ifood.repository;

import com.ifood.entity.ConnectionHistoryEntity;

import java.util.List;

public interface HealthRepository {

    void insertSignalRegistry(ConnectionHistoryEntity connectionHistoryEntity);

    ConnectionHistoryEntity findHealthHistory(String restaurantCode);

    List<ConnectionHistoryEntity> findHealthHistory(List<String> restaurantCode);
}
