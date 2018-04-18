package com.ifood;

import com.ifood.database.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.text.SimpleDateFormat;

@Configuration
public class WebConfiguration {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        Jackson2ObjectMapperBuilder b = new Jackson2ObjectMapperBuilder();
        b.indentOutput(false).dateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        return b;
    }

}
