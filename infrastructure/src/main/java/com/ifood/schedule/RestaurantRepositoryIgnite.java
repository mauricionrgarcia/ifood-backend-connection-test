package com.ifood.schedule;

import com.ifood.entity.RestaurantEntity;
import org.springframework.stereotype.Repository;

@Repository
public class RestaurantRepositoryIgnite implements RestaurantRepository {

    @Override
    public RestaurantEntity findRestaurant(String restaurantCode) {
        return new RestaurantEntity();
    }

}
