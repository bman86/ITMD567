package com.brentmennen.Service;

import com.brentmennen.Dao.TimeSeriesInterface;
import com.brentmennen.Entity.TimeSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TimeSeriesService {

    @Autowired
    @Qualifier("timeseries")
    private TimeSeriesInterface timeSeriesInterface;

    public void insertTimeSeriesData(TimeSeries timeSeries) {this.timeSeriesInterface.insertTimeSeriesToDb(timeSeries);}

}





