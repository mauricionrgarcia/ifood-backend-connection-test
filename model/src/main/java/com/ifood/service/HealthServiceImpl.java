package com.ifood.service;

import com.ifood.entity.ConnectionHistoryEntity;
import com.ifood.model.ConnectionHealthHistory;
import com.ifood.repository.HealthRepository;
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
    public ConnectionHealthHistory findHealthHistory(String restaurantCode) {
        return new ConnectionHealthHistory(healthRepository.findHealthHistory(restaurantCode));
    }

    @Override
    public List<ConnectionHealthHistory> findHealthHistory(List<String> restaurantCodes) {
        return healthRepository.findHealthHistory(restaurantCodes).stream() //
                .map(ConnectionHealthHistory::new).collect(Collectors.toList());
    }

}
