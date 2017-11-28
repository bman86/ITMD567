package com.brentmennen.Entity;

import java.util.*;
import java.util.stream.Collectors;


public class AggStockFactory {


    public AggStock construct(List<Stock> list) {

        AggStock aggStock = new AggStock();

        //get stock symbol thats passed through http rest controller
        List<String> symbl = list.stream().map(Stock::getSymbl).collect(Collectors.toList());
        String stkSymbol = symbl.get(0);
        System.out.println("sybl" + stkSymbol);


        Double avgTarget = list.stream().collect(Collectors.averagingDouble(Stock::getTarget)); //computes avg target price for selected stock
        Double avgEntry = list.stream().collect(Collectors.averagingDouble(Stock::getEntryPrice));//computes avg entry price for selected stock

        int total = list.size();         //count of entries to be divided

        Map<Boolean, Long> amntOwn =         // partition own into false and true
                list.stream()
                        .collect(Collectors.partitioningBy(
                                (Stock stock) -> (stock.getOwn() == Boolean.TRUE), Collectors.counting()
                        ));
        System.out.println(amntOwn);
        amntOwn.forEach((Boolean key, Long count) -> System.out.println(key +" count -> "+ count));

        double c = (double)amntOwn.get(true);
        double percentOwn = 0;

            if (total != 0 || c != total) {
                double ptOwn = c / total;
                percentOwn = ptOwn * 100;
            } else if (c == total){
                percentOwn = 100;
            }

        Map<Boolean, Long> amntUp =         // partition up into false and true
                list.stream()
                        .collect(Collectors.partitioningBy(
                                (Stock stock) -> (stock.getUp() == Boolean.TRUE), Collectors.counting()
                        ));
        System.out.println(amntUp);
        amntUp.forEach((Boolean key, Long count) -> System.out.println(key +" count -> "+ count));

        double d = (double)amntUp.get(true);
        double percentUp = 0;

            if (total != 0 || d != total) {
                 double ptUp = d / total;
                percentUp = ptUp * 100;
            } else if (d == total){
                 percentUp = 100;
            }


        List<String> amntTime = list.stream().map(Stock::getTime).collect(Collectors.toList());

        int occurrences = Collections.frequency(amntTime, "Long");

        System.out.println("amount time" + amntTime);
        System.out.println("occur" + occurrences);

        double t = (double)occurrences;

        double percentLong = 0;


        if (total != 0 || t != total) {
            double ptTime = t / total;
            percentLong = ptTime * 100;
        } else if (t == total){
            percentLong= 100;
        }

        aggStock.setStkSymbol(stkSymbol);
        aggStock.setAvgEntry(avgEntry);
        aggStock.setAvgTarget(avgTarget);
        aggStock.setPercentOwn(percentOwn);
        aggStock.setPercentUp(percentUp);
        aggStock.setPercentTime(percentLong);



        System.out.println("Stocks from factory!" + list);
        System.out.println("AggStocks from factory!" + aggStock);
        System.out.println("Target avg from factory!" + avgTarget);
        System.out.println("total!" + total);
        System.out.println("asdf!" + amntUp.get(true));

        System.out.println("percent own " + percentOwn);
        System.out.println("percent up " + percentUp);
        System.out.println("percent long " + percentLong);

        return aggStock;
    }
}


