package com.brentmennen.Entity;

import java.util.Map;

public class TimeSeriesFactory {
// interface/adapater class to build pojos from structured map data


    public TimeSeries construct(Map webapiinfo) {
        TimeSeries objTime = new TimeSeries();
        objTime.setOpen((String)webapiinfo.get("1. open")); //gets one field from the map
        //TODO get the rest of the info here
        //only grab the fields you need.

        return objTime;
    }
}
