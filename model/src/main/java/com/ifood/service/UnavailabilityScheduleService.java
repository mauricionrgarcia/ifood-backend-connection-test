package com.ifood.service;

import com.ifood.model.UnavailabilityReason;
import com.ifood.model.UnavailabilitySchedule;

import java.time.LocalDateTime;
import java.util.List;

public interface UnavailabilityScheduleService {

    String insertSchedule(String restaurantCode, UnavailabilityReason reason, LocalDateTime startDate, LocalDateTime endDate);

    List<UnavailabilitySchedule> fetchUnavailabilitySchedule(String restaurantCode, LocalDateTime startDate, LocalDateTime endDate);

    void deleteSchedule(String scheduleId);
}
