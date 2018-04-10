package com.ifood.service.report;

import com.ifood.domain.*;
import com.ifood.service.unavailability.schedule.UnavailabilityScheduleService;
import com.ifood.service.health.HealthService;
import org.apache.ignite.Ignite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private HealthService healthService;

    @Autowired
    private UnavailabilityScheduleService unavailabilityScheduleService;

    @Autowired
    private Ignite ignite;

    @Override
    public ConnectionReport fetchReport(String restaurantCode, LocalDateTime startDate, LocalDateTime endDate) {
        List<ConnectionHealthSignal> healthHistory = healthService.findHealthHistory(restaurantCode, startDate, endDate);
        RestaurantConnection restaurantConnection = new RestaurantConnection(healthHistory);

        List<UnavailabilitySchedule> unavailabilitySchedule = unavailabilityScheduleService.fetchUnavailabilitySchedule(restaurantCode, startDate, endDate);
        RestaurantAvailability restaurantAvailability = new RestaurantAvailability(unavailabilitySchedule);

        return new ConnectionReport(restaurantAvailability, restaurantConnection, ignite );

    }

}
