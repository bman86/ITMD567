package com.brentmennen.Dao;

import com.brentmennen.Entity.TimeSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class TimeSeriesDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void insertAlphaToDb(TimeSeries timeSeries) {
        final String sql = "INSERT INTO alpha (open) VALUES (?)";
        final String open = timeSeries.getOpen();
        jdbcTemplate.update(sql, new Object[] {open});
    }
}
