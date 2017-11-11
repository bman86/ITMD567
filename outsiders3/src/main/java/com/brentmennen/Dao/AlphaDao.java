package com.brentmennen.Dao;

import com.brentmennen.Entity.*;

import com.brentmennen.Entity.MetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

@Repository("alpha")
public class AlphaDao implements AlphaInterface {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static class AlphaRowMapper implements RowMapper<AlphaPojo>{

        @Override
        public AlphaPojo mapRow(ResultSet resultSet, int i) throws SQLException{


            AlphaPojo alphaPojo = new AlphaPojo();
            MetaData metaData = new MetaData();
            TimeSeries timeSeries = new TimeSeries();
            alphaPojo.setId(resultSet.getInt("id"));
            metaData.setMetaData(resultSet.getString("symbol"));
            timeSeries.setOpen(resultSet.getString("open"));
            return

        }

    }

    @Override
    public Collection<AlphaPojo> getAllAlpha() {
        final String sql = "SELECT id, symbol, open";
        List<AlphaPojo> alphaPojos = jdbcTemplate.query(sql, new AlphaRowMapper());
        return alphaPojos;
    }

    @Override
    public AlphaPojo getAlphaById(int id) {
        final String sql = "SELECT id, symbol, open FROM alpha WHERE id = ?";
        final AlphaPojo alphaPojo = jdbcTemplate.queryForObject(sql, new AlphaRowMapper(), id);
        return alphaPojo;
    }

    @Override
    public void insertAlphaToDb(AlphaPojo alphaPojo) {
        final String sql = "INSERT INTO alpha (symbol, open) VALUES (?, ?)";
        final String symbol = alphaPojo.metaData.getMetaData();
        final String open = alphaPojo.timeSeries.getOpen();
        jdbcTemplate.update(sql, new Object[] {symbol, open});
    }
}







