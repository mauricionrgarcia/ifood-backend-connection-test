package com.ifood.repository;

import com.ifood.entity.ConnectionHealthSignalEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class HealthRepositoryInmemory implements HealthRepository {

    private List<ConnectionHealthSignalEntity> connectionHistories = new ArrayList<>();

    @Override
    public void insertSignalRegistry(ConnectionHealthSignalEntity connectionHealthSignalEntity) {
        connectionHistories.add(connectionHealthSignalEntity);
    }

    @Override
    public List<ConnectionHealthSignalEntity> findHealthHistory(String restaurantCode, LocalDateTime startDate, LocalDateTime endDate) {
        return connectionHistories.stream()
                .filter(connectionHealthSignalEntity ->
                        connectionHealthSignalEntity.getCode().equals(restaurantCode)
                        && connectionHealthSignalEntity.getReceivedAt().isBefore(endDate)
                        && connectionHealthSignalEntity.getReceivedAt().isAfter(startDate)
                ).collect(Collectors.toList());
    }

    @Override
    public List<ConnectionHealthSignalEntity> findHealthHistory(List<String> restaurantCodes, LocalDateTime startDate, LocalDateTime endDate) {
        final List<ConnectionHealthSignalEntity> connectionHistoryEntities = new ArrayList<>();
        restaurantCodes.forEach(restaurantCode -> {
            ConnectionHealthSignalEntity connectionHealthSignalEntity = connectionHistories.stream()
                    .filter(internalConnectionHealthSignalEntity ->
                            internalConnectionHealthSignalEntity.getCode().equals(restaurantCode)
                                    && internalConnectionHealthSignalEntity.getReceivedAt().isBefore(endDate)
                                    && internalConnectionHealthSignalEntity.getReceivedAt().isAfter(startDate)
                    ).findAny().get();
            connectionHistoryEntities.add(connectionHealthSignalEntity);
        });
        return connectionHistoryEntities;
    }
}
