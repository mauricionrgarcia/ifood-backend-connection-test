package com.ifood.health;

import com.ifood.DateFormatter;
import com.ifood.model.ConnectionHealthHistory;
import com.ifood.service.HealthService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class HealthController {

    @Autowired
    private HealthService healthService;

    @ApiOperation(value="Receive the signal sent by the restaurant.")
    @RequestMapping(value = "/connection/health/{restaurant_code}", method = RequestMethod.POST)
    public void receiveHealthSignal(@PathVariable("restaurant_code") String restaurantCode){
        healthService.receiveHealthSignal(restaurantCode);
    }

    @ApiOperation(value="Search by the restaurant health for a given pediod.")
    @RequestMapping(value = "/connection/health/history/{restaurant_code}/{start_date}/{end_date}", method = RequestMethod.GET)
    public ResponseEntity fetchHealthHistory(
            @PathVariable("restaurant_code") String restaurantCode,
            @PathVariable("start_date") String startDate,
            @PathVariable("end_date") String endDate){
        return ResponseEntity.ok(healthService.findHealthHistory(restaurantCode, new DateFormatter().format(startDate), new DateFormatter().format(endDate)));
    }

    @ApiOperation(value="Search by the restaurants health for a given pediod.")
    @RequestMapping(value = "/connection/health/list", method = RequestMethod.POST)
    public ResponseEntity fetchHealthHistory(
            @RequestBody RestaurantHealthHistoriesListRequest restaurantHealthHistoriesListRequest){

        List<RestaurantHealthHistoryFeatureResponse> restaurantHealthHistories = restaurantHealthHistoriesListRequest.getRestaurantCodes().stream() //
                .map(restaurantCode -> {
                    List<ConnectionHealthHistory> connectionHealthHistories = healthService.findHealthHistory(restaurantCode, restaurantHealthHistoriesListRequest.getStartDate(), restaurantHealthHistoriesListRequest.getEndDate());
                    if (connectionHealthHistories != null && !connectionHealthHistories.isEmpty()) {
                        return new RestaurantHealthHistoryFeatureResponse(restaurantCode, connectionHealthHistories);
                    }
                    return null;
                }).filter(Objects::nonNull).collect(Collectors.toList());

        return ResponseEntity.ok(restaurantHealthHistories);
    }
}
