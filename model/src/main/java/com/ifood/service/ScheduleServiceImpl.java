package com.ifood.service;

import com.ifood.schedule.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public void insertSchedule(String restaurantCode, LocalDateTime startDate, LocalDateTime endDate) {
        // 1 - Check if the restaurant exists
        // 2 - Check if there is an schedule already inserted to the specified date
        // 3 - Insert the schedule
    }

    @Override
    public void fetchSchedule(String restaurantCode, LocalDateTime startDate, LocalDateTime endDate) {
        // 1 - Check if the restaurant exists
        // 2 - Fetch schedules
    }

    @Override
    public void deleteSchedule(String scheduleId) {
        // 1 - Check if schedule exists
        // 2 - Delete Schedule
    }
}
