package com.brentmennen.Controller;

import com.brentmennen.Entity.AggStock;
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

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.GET)
    public Collection<Stock> getAllStocks(){
        return stockService.getAllStocks();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Stock getStockById(@PathVariable("id") int id) {
        return stockService.getStockById(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/symbol/{symbl}", method = RequestMethod.GET)
    public AggStock getStockBySymbl(@PathVariable("symbl") String symbl) {
        return stockService.getStockBySymbl(symbl);

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteStockById(@PathVariable("id") int id) {
        stockService.removeStockById(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateStock(@RequestBody Stock stock) {
        stockService.updateStock(stock);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertStock(@RequestBody Stock stock) {
        stockService.insertStock(stock);
    }
}
