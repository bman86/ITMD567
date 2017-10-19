package com.brentmennen.Dao;

import com.brentmennen.Entity.Stock;

import java.util.Collection;

public interface StockDao {
    Collection<Stock> getAllStocks();

    Stock getStockById(int id);

    void removeStockById(int id);

    void updateStock(Stock stock);

    void insertStockToDb(Stock stock);
}
