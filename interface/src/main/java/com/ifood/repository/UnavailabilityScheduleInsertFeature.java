package com.ifood.repository;

import com.ifood.model.UnavailabilityReason;

import java.util.Date;

public class UnavailabilityScheduleInsertFeature {
    private String restaurantCode;

    private Date scheduleStart;

    private Date scheduleEnd;

    private UnavailabilityReason unavailabilityReason;

    public UnavailabilityScheduleInsertFeature() {
    }

    public String getRestaurantCode() {
        return restaurantCode;
    }

    public Date getScheduleStart() {
        return scheduleStart;
    }

    public Date getScheduleEnd() {
        return scheduleEnd;
    }

    public UnavailabilityReason getUnavailabilityReason() {
        return unavailabilityReason;
    }
}
