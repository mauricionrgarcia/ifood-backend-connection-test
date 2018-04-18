package com.ifood.service.health;

import com.ifood.database.entity.ConnectionHealthSignalEntity;
import com.ifood.database.entity.RestaurantEntity;
import com.ifood.database.repository.HealthRepository;
import com.ifood.database.repository.RestaurantRepository;
import com.ifood.database.repository.UnavailabilityScheduleRepository;
import com.ifood.domain.ConnectionHealthSignal;
import com.ifood.domain.RestaurantAvailability;
import com.ifood.domain.UnavailabilitySchedule;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.ifood.ignite.ApacheIgniteConfiguration.RESTAURANT_HEALTH_SIGNAL_CACHE;

@Service
public class HealthServiceImpl implements HealthService {

    @Autowired
    private HealthRepository healthRepository;

    @Autowired
    private Ignite healthRepositoryIgnite;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UnavailabilityScheduleRepository unavailabilityScheduleRepository;

    @Override
    public void receiveHealthSignal(String restaurantCode) {
        boolean notExists = !restaurantRepository.exists(restaurantCode);
        if(notExists)
            throw new IllegalArgumentException("Invalid restaurant code");

        RestaurantEntity restaurant = restaurantRepository.findByCode(restaurantCode);
        healthRepository.save(new ConnectionHealthSignalEntity(restaurant, LocalDateTime.now()));

        IgniteCache<String, Boolean> entries = healthRepositoryIgnite.getOrCreateCache(RESTAURANT_HEALTH_SIGNAL_CACHE);
        entries.put(restaurantCode, Boolean.TRUE);
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

            IgniteCache<String, Boolean> entries = healthRepositoryIgnite.getOrCreateCache(RESTAURANT_HEALTH_SIGNAL_CACHE);
            Boolean currentHealth = entries.get(restaurantCode);
            RestaurantAvailability restaurantAvailability = new RestaurantAvailability(unavailabilitySchedules);

            ConnectionHealth connectionHealth = new ConnectionHealth(restaurantCode, currentHealth, restaurantAvailability);
            connectionHealths.add(connectionHealth);
        });


        return connectionHealths;
    }

    @PostConstruct
    public void postConstruct(){
        restaurantRepository.save(new RestaurantEntity("restaurant1"));
        restaurantRepository.save(new RestaurantEntity("restaurant2"));
        restaurantRepository.save(new RestaurantEntity("restaurant3"));
        restaurantRepository.save(new RestaurantEntity("restaurant4"));
    }

}
