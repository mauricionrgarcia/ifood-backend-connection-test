package com.ifood.database.repository;

import com.ifood.database.entity.ConnectionHealthSignalEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface HealthRepository extends CrudRepository<ConnectionHealthSignalEntity, Long> {

    @Query("select healthSignal from ConnectionHealthSignalEntity healthSignal " +
            "where healthSignal.owner.code = :restaurantCode " +
            "and healthSignal.receivedAt between :startDate and :endDate")
    List<ConnectionHealthSignalEntity> findHealthHistory(
            @Param("restaurantCode") String restaurantCode,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

}
