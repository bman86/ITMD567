package com.brentmennen.Entity;


public class AggStock {

    private int id;
    private String stkSymbol;
    private Double avgEntry;
    private Double percentOwn;
    private Double percentUp;
    private Double avgTarget;
    private Double percentTime;

    public AggStock() {
    }

    public AggStock(int id, String stkSymbol, Double avgEntry, Double percentOwn, Double percentUp, Double avgTarget, Double percentTime) {
        this.id = id;
        this.stkSymbol = stkSymbol;
        this.avgEntry = avgEntry;
        this.percentOwn = percentOwn;
        this.percentUp = percentUp;
        this.avgTarget = avgTarget;
        this.percentTime = percentTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStkSymbol() {
        return stkSymbol;
    }

    public void setStkSymbol(String stkSymbol) {
        this.stkSymbol = stkSymbol;
    }

    public Double getAvgEntry() {
        return avgEntry;
    }

    public void setAvgEntry(Double avgEntry) {
        this.avgEntry = avgEntry;
    }

    public Double getPercentOwn() {
        return percentOwn;
    }

    public void setPercentOwn(Double percentOwn) {
        this.percentOwn = percentOwn;
    }

    public Double getPercentUp() {
        return percentUp;
    }

    public void setPercentUp(Double percentUp) {
        this.percentUp = percentUp;
    }

    public Double getAvgTarget() {
        return avgTarget;
    }

    public void setAvgTarget(Double avgTarget) {
        this.avgTarget = avgTarget;
    }

    public Double getPercentTime() {
        return percentTime;
    }

    public void setPercentTime(Double percentTime) {
        this.percentTime = percentTime;
    }
}

