package com.ifood.repository;

import com.ifood.entity.ConnectionHistoryEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class HealthRepositoryInmemory implements HealthRepository {

    private List<ConnectionHistoryEntity> connectionHistories = new ArrayList<>();

    @Override
    public void insertSignalRegistry(ConnectionHistoryEntity connectionHistoryEntity) {
        connectionHistories.add(connectionHistoryEntity);
    }

    @Override
    public List<ConnectionHistoryEntity> findHealthHistory(String restaurantCode, LocalDateTime startDate, LocalDateTime endDate) {
        return connectionHistories.stream()
                .filter(connectionHistoryEntity ->
                        connectionHistoryEntity.getCode().equals(restaurantCode)
                        && connectionHistoryEntity.getReceivedAt().isBefore(endDate)
                        && connectionHistoryEntity.getReceivedAt().isAfter(startDate)
                ).collect(Collectors.toList());
    }

    @Override
    public List<ConnectionHistoryEntity> findHealthHistory(List<String> restaurantCodes, LocalDateTime startDate, LocalDateTime endDate) {
        final List<ConnectionHistoryEntity> connectionHistoryEntities = new ArrayList<>();
        restaurantCodes.forEach(restaurantCode -> {
            ConnectionHistoryEntity connectionHistoryEntity = connectionHistories.stream()
                    .filter(internalConnectionHistoryEntity ->
                            internalConnectionHistoryEntity.getCode().equals(restaurantCode)
                                    && internalConnectionHistoryEntity.getReceivedAt().isBefore(endDate)
                                    && internalConnectionHistoryEntity.getReceivedAt().isAfter(startDate)
                    ).findAny().get();
            connectionHistoryEntities.add(connectionHistoryEntity);
        });
        return connectionHistoryEntities;
    }
}
