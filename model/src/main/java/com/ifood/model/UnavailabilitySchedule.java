package com.ifood.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.ifood.entity.UnavailabilityScheduleEntity;

import java.time.LocalDateTime;

/**
 * Scheduled date for an unavailability
 */
public class UnavailabilitySchedule {

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime scheduleStart;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime scheduleEnd;

    private UnavailabilityReason unavailabilityReason;

    public UnavailabilitySchedule() {
    }

    public UnavailabilitySchedule(UnavailabilityReason unavailabilityReason, LocalDateTime scheduleStart, LocalDateTime scheduleEnd) {
        if (scheduleStart.isAfter(scheduleEnd)){
            throw new IllegalArgumentException("Initial schedule is greater than the final schedule.");
        }

        this.scheduleStart = scheduleStart;
        this.scheduleEnd = scheduleEnd;
        this.unavailabilityReason = unavailabilityReason;
    }

    public UnavailabilitySchedule(UnavailabilityScheduleEntity unavailabilityScheduleEntity) {
        this.unavailabilityReason = UnavailabilityReason.valueOf(unavailabilityScheduleEntity.getReason());
        this.scheduleStart = unavailabilityScheduleEntity.getScheduleStart();
        this.scheduleEnd = unavailabilityScheduleEntity.getScheduleEnd();
    }

    public LocalDateTime getScheduleStart() {
        return scheduleStart;
    }

    public LocalDateTime getScheduleEnd() {
        return scheduleEnd;
    }

    public UnavailabilityReason getUnavailabilityReason() {
        return unavailabilityReason;
    }

    /**
     * Check if the time time provided is between the scheduled unavailability.
     * @param localDateTime - The time the will be checked
     * @return true if the given time is between the unavailability time.
     */
    public boolean isUnavailable(LocalDateTime localDateTime){
        return localDateTime.isAfter(scheduleStart) && localDateTime.isBefore(scheduleEnd);
    }
}
