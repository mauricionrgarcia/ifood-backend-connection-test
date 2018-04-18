package com.ifood.service.unavailability.schedule;

import com.ifood.database.entity.RestaurantEntity;
import com.ifood.database.entity.UnavailabilityScheduleEntity;
import com.ifood.database.repository.RestaurantRepository;
import com.ifood.database.repository.UnavailabilityScheduleRepository;
import com.ifood.domain.UnavailabilityReason;
import com.ifood.domain.UnavailabilitySchedule;
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
        RestaurantEntity restaurant = restaurantRepository.findByCode(restaurantCode);
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
        unavailabilityScheduleRepository.save(unavailabilityScheduleEntity);

        return unavailabilityScheduleEntity.getCode();
    }

    @Override
    public List<UnavailabilitySchedule> fetchUnavailabilitySchedule(String restaurantCode, LocalDateTime startDate, LocalDateTime endDate) {
        boolean notExists = !restaurantRepository.exists(restaurantCode);
        if(notExists){
            throw new IllegalArgumentException("Restaurant not found.");
        }

        return unavailabilityScheduleRepository.fetchUnavailabilitySchedule(restaurantCode, startDate, endDate) //
                .stream().map(UnavailabilitySchedule::new).collect(Collectors.toList());
    }

    @Override
    public void deleteSchedule(String scheduleCode) {
        unavailabilityScheduleRepository.deleteByCode(scheduleCode);
    }
}
