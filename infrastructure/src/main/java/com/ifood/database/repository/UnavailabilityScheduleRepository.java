package com.ifood.database.repository;

import com.ifood.database.entity.UnavailabilityScheduleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface UnavailabilityScheduleRepository extends CrudRepository<UnavailabilityScheduleEntity, Long> {

    @Query("select count(schedule) > 0 from UnavailabilityScheduleEntity schedule " +
            "where schedule.restaurant.code = :restaurantCode " +
            "and schedule.scheduleStart > :startDate and schedule.scheduleEnd < :endDate " +
            "or :startDate > schedule.scheduleStart and :startDate < schedule.scheduleEnd " +
            "or :endDate > schedule.scheduleStart and :endDate < schedule.scheduleEnd ")
    boolean exists(
            @Param("restaurantCode") String restaurantCode,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    @Query("select schedule from UnavailabilityScheduleEntity schedule " +
            "where schedule.restaurant.code = :restaurantCode " +
            "and schedule.scheduleStart > :startDate and schedule.scheduleEnd < :endDate " +
            "or :startDate > schedule.scheduleStart and :startDate < schedule.scheduleEnd " +
            "or :endDate > schedule.scheduleStart and :endDate < schedule.scheduleEnd ")
    List<UnavailabilityScheduleEntity> fetchUnavailabilitySchedule(
            @Param("restaurantCode") String restaurantCode,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    @Transactional
    void deleteByCode(String scheduleCode);

}
