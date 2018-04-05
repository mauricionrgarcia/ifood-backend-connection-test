package com.ifood.service;

import com.ifood.entity.RestaurantEntity;
import com.ifood.entity.UnavailabilityScheduleEntity;
import com.ifood.model.UnavailabilityReason;
import com.ifood.model.UnavailabilitySchedule;
import com.ifood.schedule.RestaurantRepository;
import com.ifood.schedule.UnavailabilityScheduleRepository;
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
    public void insertSchedule(String restaurantCode, UnavailabilityReason reason, LocalDateTime startDate, LocalDateTime endDate) {
        RestaurantEntity restaurant = restaurantRepository.findRestaurant(restaurantCode);
        if(restaurant == null){
            throw new IllegalArgumentException("Restaurant not found.");
        }

        if(reason == null){
            throw new IllegalArgumentException("Invalid reason.");
        }

        boolean unavailabilityExists = unavailabilityScheduleRepository.exists(restaurantCode, startDate, endDate);
        if(unavailabilityExists){
            throw new IllegalArgumentException("Unavailability schedule already exists.");
        }

        unavailabilityScheduleRepository.saveUnavailabilitySchedule(new UnavailabilityScheduleEntity(restaurant, reason.name(), startDate, endDate));
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
