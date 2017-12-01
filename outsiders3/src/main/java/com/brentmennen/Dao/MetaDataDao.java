package com.brentmennen.Dao;

import com.brentmennen.Entity.MetaData;
import com.brentmennen.Entity.TimeSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("metadata")
public class MetaDataDao implements MetaDataInterface {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static class MetaDataRowMapper implements RowMapper<MetaData> {

        @Override
        public MetaData mapRow(ResultSet resultSet, int i) throws SQLException {
            MetaData metaData = new MetaData();
            metaData.setId(resultSet.getInt("id"));
            metaData.setSymbol(resultSet.getString("symbol"));
            metaData.setOpen(resultSet.getDouble("open"));

            return metaData;
        }
    }

    public void insertMetaDataToDb(MetaData metaData, TimeSeries timeSeries) {
        final String sql = "INSERT INTO alpha (symbol, open) VALUES (?, ?)";
        final String symbol = metaData.getMetaData();
        final String open = timeSeries.getOpen();
        jdbcTemplate.update(sql, new Object[] {symbol, open});
    }

    @Override
    public MetaData getRecentMetaData() {
        final String sql = "SELECT id, symbol, open FROM alpha ORDER BY id DESC LIMIT 1";
        final MetaData metaData = jdbcTemplate.queryForObject(sql, new MetaDataRowMapper());

        return metaData;
    }
}


