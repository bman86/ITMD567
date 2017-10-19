package com.brentmennen.Dao;

import com.brentmennen.Entity.Stock;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@Qualifier("postgresStockDao")
public class PostgresStockDao implements StockDao {

    //connection and template class w/ insert delete update

    @Override
    public Collection<Stock> getAllStocks() {
        return null;
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
