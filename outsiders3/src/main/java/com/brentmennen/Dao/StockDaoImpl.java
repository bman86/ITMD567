package com.brentmennen.Dao;

import com.brentmennen.Entity.Stock;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
@Qualifier("firstDao")
public class StockDaoImpl implements StockDao {

    private static Map<Integer, Stock> stocks;

    static {

        stocks = new HashMap<Integer, Stock>(){
            {
                put(1, new Stock(1, "aapl", "Long Term"));
                put(2, new Stock(2, "amzn", "Short Term"));
                put(3, new Stock(3, "chk", "Mid Term"));

            }
        };
    }

    @Override
    public Collection<Stock> getAllStocks(){
        return this.stocks.values();
    }

    @Override
    public Stock getStockById(int id) {
        return this.stocks.get(id);
    }

    @Override
    public void removeStockById(int id) {
        this.stocks.remove(id);
    }

    @Override
    public void updateStock(Stock stock) {
        Stock s = stocks.get(stock.getId());
        s.setSymbl(stock.getSymbl());
        s.setTime(stock.getTime());
        stocks.put(stock.getId(), stock);
    }

    @Override
    public void insertStockToDb(Stock stock) {
        this.stocks.put(stock.getId(), stock);
    }
}
