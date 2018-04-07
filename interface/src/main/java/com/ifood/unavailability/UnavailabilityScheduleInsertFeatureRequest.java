package com.ifood.repository;

import com.ifood.DateFormatter;
import com.ifood.model.UnavailabilityReason;

import java.time.LocalDateTime;

public class UnavailabilityScheduleInsertFeatureRequest {

    private String restaurantCode;

    private String scheduleStart;

    private String scheduleEnd;

    private UnavailabilityReason unavailabilityReason;

    public UnavailabilityScheduleInsertFeatureRequest() {
    }

    public String getRestaurantCode() {
        return restaurantCode;
    }

    public LocalDateTime getScheduleStart() {
        return new DateFormatter().format(scheduleStart);
    }

    public LocalDateTime getScheduleEnd() {
        return new DateFormatter().format(scheduleEnd);
    }

    public UnavailabilityReason getUnavailabilityReason() {
        return unavailabilityReason;
    }
}
