package com.brentmennen.Service;

import com.brentmennen.Dao.StockDao;
import com.brentmennen.Entity.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StockService {

    @Autowired
    private StockDao stockDao;

    public Collection<Stock> getAllStocks(){
        return this.stockDao.getAllStocks();
    }

    public Stock getStockById(int id) {
        return this.stockDao.getStockById(id);
    }

    public void removeStockById(int id) {
        this.stockDao.removeStockById(id);
    }
}
