package com.brentmennen.Controller;

import com.brentmennen.Entity.Stock;
import com.brentmennen.Service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
