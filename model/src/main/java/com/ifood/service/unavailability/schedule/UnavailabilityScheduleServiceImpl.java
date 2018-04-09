package com.ifood.service.unavailability.schedule;

import com.ifood.entity.RestaurantEntity;
import com.ifood.entity.UnavailabilityScheduleEntity;
import com.ifood.domain.UnavailabilityReason;
import com.ifood.domain.UnavailabilitySchedule;
import com.ifood.repository.RestaurantRepository;
import com.ifood.repository.UnavailabilityScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnavailabilityScheduleServiceImpl implements UnavailabilityScheduleService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UnavailabilityScheduleRepository unavailabilityScheduleRepository;

    @Override
    public String insertSchedule(String restaurantCode, UnavailabilityReason reason, LocalDateTime startDate, LocalDateTime endDate) {
        RestaurantEntity restaurant = restaurantRepository.findRestaurant(restaurantCode);
        if(restaurant == null){
            throw new IllegalArgumentException("Restaurant not found.");
        }

        if(reason == null){
            throw new IllegalArgumentException("Invalid reason.");
        }

        boolean unavailabilityExists = unavailabilityScheduleRepository.exists(restaurantCode, startDate, endDate);
        if(unavailabilityExists){
            throw new IllegalArgumentException("Unavailability repository already exists.");
        }

        UnavailabilityScheduleEntity unavailabilityScheduleEntity = new UnavailabilityScheduleEntity(restaurant, reason.name(), startDate, endDate);
        unavailabilityScheduleRepository.saveUnavailabilitySchedule(unavailabilityScheduleEntity);

        return unavailabilityScheduleEntity.getCode();
    }

    @Override
    public List<UnavailabilitySchedule> fetchUnavailabilitySchedule(String restaurantCode, LocalDateTime startDate, LocalDateTime endDate) {
        return unavailabilityScheduleRepository.fetchUnavailabilitySchedule(restaurantCode, startDate, endDate) //
                .stream().map(UnavailabilitySchedule::new).collect(Collectors.toList());
    }

    @Override
    public void deleteSchedule(String scheduleId) {
        unavailabilityScheduleRepository.deleteSchedule(scheduleId);
    }
}
