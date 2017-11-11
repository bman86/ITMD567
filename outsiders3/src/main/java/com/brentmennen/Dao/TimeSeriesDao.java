package com.brentmennen.Dao;

import com.brentmennen.Entity.TimeSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("timeseries")
public class TimeSeriesDao implements TimeSeriesInterface {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void insertTimeSeriesToDb(TimeSeries timeSeries) {
        final String sql = "INSERT INTO alpha (open) VALUES (?)";
        final String open = timeSeries.getOpen();
        jdbcTemplate.update(sql, new Object[] {open});
    }
}

