package com.ifood.repository;

import com.ifood.model.UnavailabilitySchedule;
import com.ifood.service.UnavailabilityScheduleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
public class UnavailabilityScheduleController {

    @Autowired
    private UnavailabilityScheduleService unavailabilityScheduleService;

    @ApiOperation(value="Fetch the unavailability repository for a given period.")
    @RequestMapping(value="/connection/repository/unavailability/{restaurant_code}/{start_date}/{end_date}", method = RequestMethod.GET)
    public ResponseEntity fetchUnavailabilitySchedule(
            @PathVariable("restaurant_code") String restaurantCode, //
            @PathVariable("start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @PathVariable("end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        List<UnavailabilitySchedule> unavailabilitySchedule =
                unavailabilityScheduleService.fetchUnavailabilitySchedule(restaurantCode,
                        LocalDateTime.ofInstant(startDate.toInstant(), ZoneId.systemDefault()),
                        LocalDateTime.ofInstant(endDate.toInstant(), ZoneId.systemDefault()));
        return ResponseEntity.ok(unavailabilitySchedule);
    }

    @ApiOperation(value="Insert an unavailability repository.")
    @RequestMapping(value="/connection/repository/unavailability", method = RequestMethod.POST)
    public ResponseEntity insertUnavailabilitySchedule(
            @RequestBody UnavailabilityScheduleInsertFeature unavailabilityScheduleFeature){

        unavailabilityScheduleService.insertSchedule( //
                unavailabilityScheduleFeature.getRestaurantCode(), //
                unavailabilityScheduleFeature.getUnavailabilityReason(), //
                unavailabilityScheduleFeature.getScheduleStart().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(), //
                unavailabilityScheduleFeature.getScheduleEnd().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value="Delete an unavailability schedule.")
    @RequestMapping(value="/connection/repository/unavailability/{schedule_code}", method = RequestMethod.POST)
    public ResponseEntity deleteUnavailabilitySchedule(@PathVariable("schedule_code") String scheduleId){

        unavailabilityScheduleService.deleteSchedule(scheduleId);

        return ResponseEntity.ok().build();
    }

}
