package com.ifood.service.health;

import com.ifood.domain.ConnectionHealthSignal;
import com.ifood.domain.RestaurantAvailability;
import com.ifood.domain.UnavailabilitySchedule;
import com.ifood.entity.ConnectionHealthSignalEntity;
import com.ifood.ignite.repository.HealthRepositoryIgnite;
import com.ifood.repository.HealthRepository;
import com.ifood.repository.RestaurantRepository;
import com.ifood.repository.UnavailabilityScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HealthServiceImpl implements HealthService {

    @Autowired
    private HealthRepository healthRepository;

    @Autowired
    private HealthRepositoryIgnite healthRepositoryIgnite;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UnavailabilityScheduleRepository unavailabilityScheduleRepository;

    @Override
    public void receiveHealthSignal(String restaurantCode) {
        healthRepository.insertSignalRegistry(new ConnectionHealthSignalEntity(restaurantCode, LocalDateTime.now()));

        healthRepositoryIgnite.save(restaurantCode, Boolean.TRUE);
    }

    @Override
    public List<ConnectionHealthSignal> findHealthHistory(String restaurantCode, LocalDateTime startDate, LocalDateTime endDate) {
        return healthRepository.findHealthHistory(restaurantCode, startDate, endDate).stream() //
                .map(ConnectionHealthSignal::new).collect(Collectors.toList());
    }

    @Override
    public List<ConnectionHealth> checkRestaurantsConnection(List<String> restaurantCodes) {
        List<ConnectionHealth> connectionHealths = new ArrayList<>();

        restaurantCodes.forEach(restaurantCode -> {
            LocalDateTime currentTime = LocalDateTime.now();
            LocalDateTime maxTimeOfUnavailability = currentTime.plusMinutes(2);
            List<UnavailabilitySchedule> unavailabilitySchedules =
                    unavailabilityScheduleRepository.fetchUnavailabilitySchedule(restaurantCode, currentTime, maxTimeOfUnavailability)
                            .stream().map(UnavailabilitySchedule::new).collect(Collectors.toList());

            boolean currentHealth = healthRepositoryIgnite.exists(restaurantCode);
            RestaurantAvailability restaurantAvailability = new RestaurantAvailability(unavailabilitySchedules);

            ConnectionHealth connectionHealth = new ConnectionHealth(restaurantCode, currentHealth, restaurantAvailability);
            connectionHealths.add(connectionHealth);
        });


        return connectionHealths;
    }

}
