package com.brentmennen.Controller;

import com.brentmennen.Entity.Stock;
import com.brentmennen.Service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Stock> getAllStocks(){
        return stockService.getAllStocks();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Stock getStockById(@PathVariable("id") int id) {
        return stockService.getStockById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteStockById(@PathVariable("id") int id) {
        stockService.removeStockById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteStockById(@RequestBody Stock stock) {
        stockService.updateStock(stock);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertStock(@RequestBody Stock stock) {
        stockService.insertStock(stock);
    }
}
