package com.brentmennen.Dao;


import com.brentmennen.Entity.*;
import com.brentmennen.Parser;
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



    private static class StockRowMapper implements RowMapper<Stock>{

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
    }
    @Override
    public Collection<Stock> getAllStocks() {

        final String sql = "SELECT id, symbl, own, entryPrice, up, target, time FROM outsiders";
        List<Stock> stocks = jdbcTemplate.query(sql, new StockRowMapper());

        return stocks;
    }

    @Override
    public Stock getStockById(int id) {
        final String sql = "SELECT id, symbl, own, entryPrice, up, target, time FROM outsiders WHERE id = ?";
        final Stock stock = jdbcTemplate.queryForObject(sql, new StockRowMapper(), id);

        return stock;
    }

    //allows external get that passes symbl variable to access data for specific stock
    @Override
    public AggStock getStockBySymbl(String symbl) {

        final String sql = "SELECT * FROM outsiders WHERE symbl = ?";
        final List<Stock> stocks = jdbcTemplate.query(sql, new StockRowMapper(), symbl);

        AggStock aggStock = new AggStockFactory().construct(stocks);

        return aggStock;
    }
    @Override
    public void removeStockById(int id) {
        final String sql = "DELETE FROM outsiders WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void updateStock(Stock stock) {
        final String sql = "UPDATE outsiders SET symbl = ?, own = ?, entryPrice = ?, up = ?, target = ?, time = ? WHERE id = ?";
        final int id = stock.getId();
        final String symbl = stock.getSymbl();
        final Boolean own = stock.getOwn();
        final Double entryPrice = stock.getEntryPrice();
        final Boolean up = stock.getUp();
        final Double target = stock.getTarget();
        final String time = stock.getTime();
        jdbcTemplate.update(sql, new Object[] {symbl, own, entryPrice, up, target, time, id});

    }

    @Override
    public void insertStockToDb(Stock stock) {
        final String sql = "INSERT INTO outsiders (symbl, own, entryPrice, up, target, time) VALUES (?, ?, ?, ?, ?, ?)";
        final String symbl = stock.getSymbl();
        final Boolean own = stock.getOwn();
        final Double entryPrice = stock.getEntryPrice();
        final Boolean up = stock.getUp();
        final Double target = stock.getTarget();
        final String time = stock.getTime();
        jdbcTemplate.update(sql, new Object[] {symbl, own, entryPrice, up, target, time});

    }
}
