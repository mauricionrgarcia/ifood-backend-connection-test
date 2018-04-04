package com.ifood.model;

import java.time.LocalDateTime;

/**
 * Scheduled date for an unavailability
 */
public class UnavailabilitySchedule {

    private LocalDateTime scheduleStart;

    private LocalDateTime scheduleEnd;

    private UnavailabilityReason unavailabilityReason;

    public UnavailabilitySchedule(UnavailabilityReason unavailabilityReason, LocalDateTime scheduleStart, LocalDateTime scheduleEnd) {
        this(scheduleStart, scheduleEnd);
        this.unavailabilityReason = unavailabilityReason;
    }

    public UnavailabilitySchedule(LocalDateTime scheduleStart, LocalDateTime scheduleEnd) {
        if (scheduleStart.isAfter(scheduleEnd)){
            throw new IllegalArgumentException("Initial schedule is greater than the final schedule.");
        }

        this.scheduleStart = scheduleStart;
        this.scheduleEnd = scheduleEnd;
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
