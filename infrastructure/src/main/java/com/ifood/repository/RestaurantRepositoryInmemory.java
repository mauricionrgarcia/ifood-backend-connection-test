package com.ifood.repository;

import com.ifood.entity.RestaurantEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RestaurantRepositoryInmemory implements RestaurantRepository {

    private List<RestaurantEntity> list = List.of( //
            new RestaurantEntity("restaurant1"),
            new RestaurantEntity("restaurant2"),
            new RestaurantEntity("restaurant3"),
            new RestaurantEntity("restaurant4")
    );

    @Override
    public RestaurantEntity findRestaurant(String restaurantCode) {
        return list.stream()//
                .filter(restaurantEntity -> //
                        restaurantEntity.getCode().equals(restaurantCode) //
                ) //
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Restaurant not found."));
    }

}
