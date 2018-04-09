package com.ifood.inmemory.repository;

import com.ifood.entity.UnavailabilityScheduleEntity;
import com.ifood.repository.UnavailabilityScheduleRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UnavailabilityScheduleRepositoryInmemory implements UnavailabilityScheduleRepository {
    private static List<UnavailabilityScheduleEntity> list = new ArrayList<>();

    @Override
    public boolean exists(String restaurantCode, LocalDateTime startDate, LocalDateTime endDate) {
        return false;
    }

    @Override
    public List<UnavailabilityScheduleEntity> fetchUnavailabilitySchedule(String restaurantCode, LocalDateTime startDate, LocalDateTime endDate) {
        return list.stream().filter(unavailabilityScheduleEntity ->
                (unavailabilityScheduleEntity.getScheduleStart().isAfter(startDate) && unavailabilityScheduleEntity.getScheduleEnd().isBefore(endDate)) ||
                        (startDate.isAfter(unavailabilityScheduleEntity.getScheduleStart()) && startDate.isBefore(unavailabilityScheduleEntity.getScheduleEnd()) ||
                        (endDate.isAfter(unavailabilityScheduleEntity.getScheduleStart()) && endDate.isBefore(unavailabilityScheduleEntity.getScheduleEnd())))

        ).collect(Collectors.toList());
    }

    @Override
    public void deleteSchedule(String scheduleCode) {
        list.stream() //
                .filter(unavailabilityScheduleEntity -> unavailabilityScheduleEntity.getCode().equals(scheduleCode)).findFirst()
                .ifPresent(unavailabilityScheduleEntity -> list.remove(unavailabilityScheduleEntity));
    }

    @Override
    public void saveUnavailabilitySchedule(UnavailabilityScheduleEntity unavailabilityScheduleEntity) {
        list.add(unavailabilityScheduleEntity);
    }
}
