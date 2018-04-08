package com.ifood.domain;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Period of time assessed.
 *
 * It represents the period between time from the first to the second signal.
 *
 */
public class ConnectionPeriodAssessed {

    private ConnectionHealthSignal firstHealthSignal;
    private ConnectionHealthSignal secondHealthSignal;

    public ConnectionPeriodAssessed(ConnectionHealthSignal firstHealthSignal, ConnectionHealthSignal secondHealthSignal) {
        this.firstHealthSignal = firstHealthSignal;
        this.secondHealthSignal = secondHealthSignal;
    }

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
