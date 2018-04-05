package com.ifood.schedule;

import com.ifood.entity.RestaurantEntity;

public interface RestaurantRepository {

    RestaurantEntity findRestaurant(String restaurantCode);

}
