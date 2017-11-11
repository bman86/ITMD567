package com.brentmennen.Controller;

import com.brentmennen.Entity.TimeSeries;
import com.brentmennen.Service.TimeSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeSeriesController {

    @Autowired
    private TimeSeriesService timeSeriesService;

    @RequestMapping(method = RequestMethod.GET)
    public void insertTimeSeriesData(@RequestBody TimeSeries timeSeries) {timeSeriesService.insertTimeSeriesData(timeSeries);}
}

