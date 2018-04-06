package com.ifood.repository;

import com.ifood.entity.UnavailabilityScheduleEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface UnavailabilityScheduleRepository {

    boolean exists(String restaurantCode, LocalDateTime startDate, LocalDateTime endDate);

    List<UnavailabilityScheduleEntity> fetchUnavailabilitySchedule(String restaurantCode, LocalDateTime startDate, LocalDateTime endDate);

    void deleteSchedule(String scheduleId);

    void saveUnavailabilitySchedule(UnavailabilityScheduleEntity unavailabilityScheduleEntity);
}
