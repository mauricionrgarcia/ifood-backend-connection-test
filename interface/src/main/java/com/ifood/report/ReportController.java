package com.ifood.report;

import com.ifood.DateFormatter;
import com.ifood.domain.ConnectionReport;
import com.ifood.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @RequestMapping(value="/connection/report/{restaurant_code}", method = RequestMethod.GET)
    public ResponseEntity fetchReport(
            @PathVariable("restaurant_code") String restaurantCode,
            @PathVariable("start_date") String startDate,
            @PathVariable("end_date") String endDate) {
        ConnectionReport report = reportService.fetchReport(
                restaurantCode,
                new DateFormatter().format(startDate),
                new DateFormatter().format(endDate)
        );
        return ResponseEntity.ok(report);
    }
}
