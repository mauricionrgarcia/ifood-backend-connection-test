package com.ifood.repository;

import com.ifood.entity.RestaurantEntity;

public interface RestaurantRepository {

    RestaurantEntity findRestaurant(String restaurantCode);

}
