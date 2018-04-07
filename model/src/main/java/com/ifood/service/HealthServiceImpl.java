package com.ifood.service;

import com.ifood.entity.ConnectionHistoryEntity;
import com.ifood.model.ConnectionHealthHistory;
import com.ifood.unavailability.HealthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HealthServiceImpl implements HealthService {

    @Autowired
    private HealthRepository healthRepository;

    @Override
    public void receiveHealthSignal(String restaurantCode) {
        healthRepository.insertSignalRegistry(new ConnectionHistoryEntity(restaurantCode, LocalDateTime.now()));
    }

    @Override
    public List<ConnectionHealthHistory> findHealthHistory(String restaurantCode, LocalDateTime startDate, LocalDateTime endDate) {
        return healthRepository.findHealthHistory(restaurantCode, startDate, endDate).stream() //
                .map(ConnectionHealthHistory::new).collect(Collectors.toList());
    }

    @Override
    public List<ConnectionHealthHistory> findHealthHistory(List<String> restaurantCodes, LocalDateTime startDate, LocalDateTime endDate) {
        return healthRepository.findHealthHistory(restaurantCodes, startDate, endDate).stream() //
                .map(ConnectionHealthHistory::new).collect(Collectors.toList());
    }

}
