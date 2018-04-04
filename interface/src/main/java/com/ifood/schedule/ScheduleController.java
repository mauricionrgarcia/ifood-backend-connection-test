package com.ifood.schedule;

import com.ifood.service.ScheduleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @ApiOperation(value="Fetch the unavailability schedule for a given period.")
    @RequestMapping(value="/connection/schedule/unavailability/{restaurant_code}/{start_date}/{end_date}", method = RequestMethod.GET)
    public ResponseEntity fetchScheduleUnavailability(
            @PathVariable("restaurant_code") String restaurantCode, //
            @PathVariable("start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime startDate,
            @PathVariable("end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime endDate) {

       throw new UnsupportedOperationException();
    }

    @ApiOperation(value="Insert an unavailability schedule.")
    @RequestMapping(value="/connection/schedule/unavailability/{restaurant_code}", method = RequestMethod.POST)
    public ResponseEntity insertScheduleUnavailability(
            @PathVariable("restaurant_code") String restaurantCode, //
            @RequestParam(value="startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime startDate,
            @RequestParam(value="endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime endDate){

        scheduleService.insertSchedule(restaurantCode, startDate, endDate);

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value="Delete an unavailability schedule.")
    @RequestMapping(value="/connection/schedule/unavailability/{schedule_id}", method = RequestMethod.POST)
    public ResponseEntity deleteScheduleUnavailability(@PathVariable("schedule_id") String scheduleId){

        scheduleService.deleteSchedule(scheduleId);

        return ResponseEntity.ok().build();
    }

}
