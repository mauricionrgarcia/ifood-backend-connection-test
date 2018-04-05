package com.ifood.schedule;

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

    @ApiOperation(value="Fetch the unavailability schedule for a given period.")
    @RequestMapping(value="/connection/schedule/unavailability/{restaurant_code}/{start_date}/{end_date}", method = RequestMethod.GET)
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

    @ApiOperation(value="Insert an unavailability schedule.")
    @RequestMapping(value="/connection/schedule/unavailability", method = RequestMethod.POST)
    public ResponseEntity insertUnavailabilitySchedule(
            @RequestBody UnavailabilityScheduleInsertFeature unavailabilityScheduleFeature){

        unavailabilityScheduleService.insertSchedule(
                unavailabilityScheduleFeature.getRestaurantCode(), unavailabilityScheduleFeature.getUnavailabilityReason(), unavailabilityScheduleFeature.getScheduleStart(), unavailabilityScheduleFeature.getScheduleEnd());

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value="Delete an unavailability schedule.")
    @RequestMapping(value="/connection/schedule/unavailability/{schedule_id}", method = RequestMethod.POST)
    public ResponseEntity deleteUnavailabilitySchedule(@PathVariable("schedule_id") String scheduleId){

        unavailabilityScheduleService.deleteSchedule(scheduleId);

        return ResponseEntity.ok().build();
    }

}
