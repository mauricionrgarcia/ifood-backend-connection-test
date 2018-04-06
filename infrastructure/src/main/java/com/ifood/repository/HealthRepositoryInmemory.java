package com.ifood.repository;

import com.ifood.entity.ConnectionHistoryEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HealthRepositoryInmemory implements HealthRepository {

    @Override
    public void insertSignalRegistry(ConnectionHistoryEntity connectionHistoryEntity) {

    }

    @Override
    public ConnectionHistoryEntity findHealthHistory(String restaurantCode) {
        return null;
    }

    @Override
    public List<ConnectionHistoryEntity> findHealthHistory(List<String> restaurantCode) {
        return null;
    }
}
