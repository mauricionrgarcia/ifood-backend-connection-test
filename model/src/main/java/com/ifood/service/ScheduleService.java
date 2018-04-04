package com.ifood.service;

import java.time.LocalDateTime;

public interface ScheduleService {

    void insertSchedule(String restaurantCode, LocalDateTime startDate, LocalDateTime endDate);

    void fetchSchedule(String restaurantCode, LocalDateTime startDate, LocalDateTime endDate);

    void deleteSchedule(String scheduleId);
}
