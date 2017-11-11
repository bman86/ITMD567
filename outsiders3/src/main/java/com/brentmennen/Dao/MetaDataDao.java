package com.brentmennen.Dao;

import com.brentmennen.Entity.MetaData;
import com.brentmennen.Entity.TimeSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("metadata")
public class MetaDataDao implements MetaDataInterface {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertMetaDataToDb(MetaData metaData, TimeSeries timeSeries) {
        final String sql = "INSERT INTO alpha (symbol, open) VALUES (?, ?)";
        final String symbol = metaData.getMetaData();
        final String open = timeSeries.getOpen();
        jdbcTemplate.update(sql, new Object[] {symbol, open});
    }
}


