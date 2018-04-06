package com.ifood.health;

import com.ifood.model.ConnectionHealthHistory;
import com.ifood.service.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HealthController {

    @Autowired
    private HealthService healthService;

    @RequestMapping(value = "/connection/health/{restaurant_code}", method = RequestMethod.POST)
    public void receiveHealthSignal(@PathVariable("restaurant_code") String restaurantCode){
        healthService.receiveHealthSignal(restaurantCode);
    }

    @RequestMapping(value = "/connection/health/history/{restaurant_code}", method = RequestMethod.GET)
    public ConnectionHealthHistory fetchHealthHistory(String restaurantCode){
        return healthService.findHealthHistory(restaurantCode);
    }

    @RequestMapping(value = "/connection/health/list", method = RequestMethod.POST)
    public List<ConnectionHealthHistory> fetchHealthHistory(List<String> restaurantCodes){
        return healthService.findHealthHistory(restaurantCodes);
    }
}
