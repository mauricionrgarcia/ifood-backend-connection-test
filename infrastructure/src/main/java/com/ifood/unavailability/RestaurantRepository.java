package com.ifood.unavailability;

import com.ifood.entity.RestaurantEntity;

public interface RestaurantRepository {

    RestaurantEntity findRestaurant(String restaurantCode);

}
