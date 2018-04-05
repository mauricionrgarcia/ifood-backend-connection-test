package com.ifood.schedule;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.ifood.model.UnavailabilityReason;

import java.time.LocalDateTime;

public class UnavailabilityScheduleInsertFeature {
    private String restaurantCode;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime scheduleStart;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime scheduleEnd;

    private UnavailabilityReason unavailabilityReason;

    public UnavailabilityScheduleInsertFeature() {
    }

    public String getRestaurantCode() {
        return restaurantCode;
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
}
