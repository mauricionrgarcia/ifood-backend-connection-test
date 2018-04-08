package com.ifood.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Period of time assessed.
 *
 * It represents the period between time from the first to the second signal.
 *
 */
public class ConnectionPeriodAssessed {

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private ConnectionHealthSignal firstHealthSignal;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private ConnectionHealthSignal secondHealthSignal;

    public ConnectionPeriodAssessed(ConnectionHealthSignal firstHealthSignal, ConnectionHealthSignal secondHealthSignal) {
        this.firstHealthSignal = firstHealthSignal;
        this.secondHealthSignal = secondHealthSignal;
    }

    @JsonIgnore
    public Duration getTimeBetween(){
        return firstHealthSignal.getDifferenceBetweenSignal(secondHealthSignal);
    }

    public LocalDateTime getFirstHealthSignal() {
        return firstHealthSignal.getReceivedAt();
    }

    public LocalDateTime getSecondHealthSignal() {
        return secondHealthSignal.getReceivedAt();
    }
}
