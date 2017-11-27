package com.brentmennen.Entity;


public class MetaData {

    private String metaData;
    private int id;
    private String symbol;
    private Double open;

    public MetaData(String metaData) {
        this.metaData = metaData;
    }

    public MetaData(){}

    public MetaData(int id, String symbol, Double open) {
        this.id = id;
        this.symbol = symbol;
        this.open = open;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public String getMetaData() {
        return metaData;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

}
