package com.brentmennen.Dao;

import com.brentmennen.Entity.Stock;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class StockDao {

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

    public Collection<Stock> getAllStocks(){
        return this.stocks.values();
    }

    public Stock getStockById(int id) {
        return this.stocks.get(id);
    }

    public void removeStockById(int id) {
        this.stocks.remove(id);
    }
}
