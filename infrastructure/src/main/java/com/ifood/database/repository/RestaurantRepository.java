package com.ifood.database.repository;

import com.ifood.database.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RestaurantRepository extends CrudRepository<RestaurantEntity, Long> {

    RestaurantEntity findByCode(String restaurantCode);

    @Query("select count(restaurant) > 0 from RestaurantEntity restaurant where restaurant.code = :code")
    boolean exists(@Param("code") String restaurantCode);

}
