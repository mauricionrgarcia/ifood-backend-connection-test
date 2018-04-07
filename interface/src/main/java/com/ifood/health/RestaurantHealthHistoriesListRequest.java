package com.ifood.health;

import com.ifood.DateFormatter;

import java.time.LocalDateTime;
import java.util.List;

public class RestaurantHealthHistoriesListRequest {
    private List<String> restaurantCodes;
    private String startDate;
    private String endDate;

    public List<String> getRestaurantCodes() {
        return restaurantCodes;
    }

    public LocalDateTime getStartDate() {
        return new DateFormatter().format(startDate);
    }

    public LocalDateTime getEndDate() {
        return new DateFormatter().format(endDate);
    }
}
