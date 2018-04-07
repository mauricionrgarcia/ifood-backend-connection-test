package com.ifood.repository;

import com.ifood.DateFormatter;
import com.ifood.model.UnavailabilitySchedule;
import com.ifood.service.UnavailabilityScheduleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UnavailabilityScheduleController {

    @Autowired
    private UnavailabilityScheduleService unavailabilityScheduleService;

    @ApiOperation(value="Fetch the repository repository for a given period.")
    @RequestMapping(value="/connection/repository/repository/{restaurant_code}/{start_date}/{end_date}", method = RequestMethod.GET)
    public ResponseEntity fetchUnavailabilitySchedule(
            @PathVariable("restaurant_code") String restaurantCode, //
            @PathVariable("start_date") String startDate,
            @PathVariable("end_date") String endDate) {
        List<UnavailabilitySchedule> unavailabilitySchedule =
                unavailabilityScheduleService.fetchUnavailabilitySchedule(restaurantCode,
                        new DateFormatter().format(startDate),
                        new DateFormatter().format(endDate));
        return ResponseEntity.ok(unavailabilitySchedule);
    }

    @ApiOperation(value="Insert an repository repository.")
    @RequestMapping(value="/connection/repository/repository", method = RequestMethod.POST)
    public ResponseEntity insertUnavailabilitySchedule(
            @RequestBody UnavailabilityScheduleInsertFeatureRequest unavailabilityScheduleFeature){

        unavailabilityScheduleService.insertSchedule( //
                unavailabilityScheduleFeature.getRestaurantCode(), //
                unavailabilityScheduleFeature.getUnavailabilityReason(), //
                unavailabilityScheduleFeature.getScheduleStart(), //
                unavailabilityScheduleFeature.getScheduleEnd());

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value="Delete an repository schedule.")
    @RequestMapping(value="/connection/repository/repository/{schedule_code}", method = RequestMethod.POST)
    public ResponseEntity deleteUnavailabilitySchedule(@PathVariable("schedule_code") String scheduleId){

        unavailabilityScheduleService.deleteSchedule(scheduleId);

        return ResponseEntity.ok().build();
    }

}