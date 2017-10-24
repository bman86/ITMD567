package com.brentmennen.Dao;

import com.brentmennen.Entity.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

@Repository("postgres")
public class PostgresStockDao implements StockDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Collection<Stock> getAllStocks() {
//        SELECT select_list FROM table_expression [sort_specification]
        final String sql = "SELECT id, symbl, own, entryPrice, up, target, time FROM outsiders";
        List<Stock> stocks = jdbcTemplate.query(sql, new RowMapper<Stock>() {
            @Override
            public Stock mapRow(ResultSet resultSet, int i) throws SQLException {
                Stock stock = new Stock();
                stock.setId(resultSet.getInt("id"));
                stock.setSymbl(resultSet.getString("symbl"));
                stock.setOwn(resultSet.getBoolean("own"));
                stock.setEntryPrice(resultSet.getDouble("entryPrice"));
                stock.setUp(resultSet.getBoolean("up"));
                stock.setTarget(resultSet.getDouble("target"));
                stock.setTime(resultSet.getString("time"));
                return stock;
            }
        });

        return stocks;
    }


    @Override
    public Stock getStockById(int id) {
        return null;
    }

    @Override
    public void removeStockById(int id) {

    }

    @Override
    public void updateStock(Stock stock) {

    }

    @Override
    public void insertStockToDb(Stock stock) {

    }
}
