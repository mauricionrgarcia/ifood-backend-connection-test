package com.ifood;

/**
 * Reason accepted for unavailabilities.
 */
public enum UnavailabilityReason {
    DELIVERY_STACK_LACK ("lack of delivery staff"),
    CONNECTION_ISSUES("connection issues (bad internet)"),
    OVERLOADED("overloaded due to offline orders"),
    HOLIDAYS("holidays");

    private String description;

    UnavailabilityReason(String description) {
        this.description = description;
    }
}
