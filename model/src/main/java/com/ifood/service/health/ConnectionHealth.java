package com.ifood.service.health;

import com.ifood.domain.RestaurantAvailability;

public class ConnectionHealth {

    private String restaurantCode;
    private Boolean currentHealth;
    private RestaurantAvailability restaurantAvailability;

    ConnectionHealth(String restaurantCode, Boolean currentHealth, RestaurantAvailability restaurantAvailability) {
        this.restaurantCode = restaurantCode;
        this.currentHealth = currentHealth;
        this.restaurantAvailability = restaurantAvailability;
    }

    public String getRestaurantCode() {
        return restaurantCode;
    }

    public boolean isOnline(){
        if (!restaurantAvailability.isCurrentlyOpen()) {
            return false;
        }

        if(currentHealth == null){
            return false;
        }

        return currentHealth;
    }

}
