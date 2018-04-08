package com.ifood.service;

import com.ifood.domain.ConnectionHealthSignal;
import com.ifood.entity.ConnectionHealthSignalEntity;
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
        healthRepository.insertSignalRegistry(new ConnectionHealthSignalEntity(restaurantCode, LocalDateTime.now()));
    }

    @Override
    public List<ConnectionHealthSignal> findHealthHistory(String restaurantCode, LocalDateTime startDate, LocalDateTime endDate) {
        return healthRepository.findHealthHistory(restaurantCode, startDate, endDate).stream() //
                .map(ConnectionHealthSignal::new).collect(Collectors.toList());
    }

}
