package com.ifood.model;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Health connection signal received from restaurant.
 */
public class ConnectionHealthSignal implements Comparable<ConnectionHealthSignal> {

    private LocalDateTime receivedAt;

    public ConnectionHealthSignal(LocalDateTime receivedAt) {
        this.receivedAt = receivedAt;
    }

    public Duration getDifferenceBetweenSignal(ConnectionHealthSignal nextConnectionHealthSignal) {
        return Duration.between(this.receivedAt, nextConnectionHealthSignal.receivedAt);
    }

    @Override
    public int compareTo(ConnectionHealthSignal otherObject) {
        return this.receivedAt.compareTo(otherObject.receivedAt);
    }

}
