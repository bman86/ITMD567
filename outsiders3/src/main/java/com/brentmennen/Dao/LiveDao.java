package com.brentmennen.Dao;

import com.brentmennen.Entity.Live;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("liveDataBase")
public class LiveDao implements LiveRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static class LiveRowMapper implements RowMapper<Live> {

        @Override
        public Live mapRow(ResultSet resultSet, int i) throws SQLException {
            Live live = new Live();
            live.setId(resultSet.getLong("id"));
            live.setMetaData(resultSet.getString("metadata"));
            live.setTimeSeriesData(resultSet.getBoolean("time"));
            return live;
        }
    }


    @Override
    public void insertStockToDb(Live stock) {
        final String sql = "INSERT INTO nowstock (metadata, time) VALUES (?, ?)";
        final String metadata = live.getSymbl();
        final Boolean own = stock.getOwn();
        final Double entryPrice = stock.getEntryPrice();
        final Boolean up = stock.getUp();
        final Double target = stock.getTarget();
        final String time = stock.getTime();
        jdbcTemplate.update(sql, new Object[] {symbl, own, entryPrice, up, target, time});

    }
}
