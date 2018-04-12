package com.ifood.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.ifood.database.entity.ConnectionHealthSignalEntity;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Health connection signal received from restaurant.
 */
public class ConnectionHealthSignal implements Comparable<ConnectionHealthSignal> {

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime receivedAt;

    public ConnectionHealthSignal() {
    }

    public ConnectionHealthSignal(ConnectionHealthSignalEntity healthHistory) {
        this.receivedAt = healthHistory.getReceivedAt();
    }

    public ConnectionHealthSignal(LocalDateTime receivedAt) {
        this.receivedAt = receivedAt;
    }

    public Duration getDifferenceBetweenSignal(ConnectionHealthSignal nextConnectionHealthSignal) {
        return Duration.between(this.receivedAt, nextConnectionHealthSignal.receivedAt);
    }

    public LocalDateTime getReceivedAt() {
        return receivedAt;
    }

    @Override
    public int compareTo(ConnectionHealthSignal otherObject) {
        return this.receivedAt.compareTo(otherObject.receivedAt);
    }

}
