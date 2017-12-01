package com.brentmennen.Entity;

public class Stock {

    private int id;
    private String symbl;
    private Boolean own;
    private Double entryPrice;
    private Boolean up;
    private Double target;
    private String time;

    public Stock(int id, String symbl, Boolean own, Double entryPrice, Boolean up, Double target, String time) {
        this.id = id;
        this.symbl = symbl;
        this.own = own;
        this.entryPrice = entryPrice;
        this.up = up;
        this.target = target;
        this.time = time;
    }

    public Stock(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymbl() {
        return symbl;
    }

    public void setSymbl(String symbl) {
        this.symbl = symbl;
    }

    public Boolean getOwn() {
        return own;
    }

    public void setOwn(Boolean own) {
        this.own = own;
    }

    public Double getEntryPrice() {
        return entryPrice;
    }

    public void setEntryPrice(Double entryPrice) {
        this.entryPrice = entryPrice;
    }

    public Boolean getUp() {
        return up;
    }

    public void setUp(Boolean up) {
        this.up = up;
    }

    public Double getTarget() {
        return target;
    }

    public void setTarget(Double target) {
        this.target = target;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", symbl='" + symbl + '\'' +
                ", own=" + own +
                ", entryPrice=" + entryPrice +
                ", up=" + up +
                ", target=" + target +
                ", time='" + time + '\'' +
                '}';
    }
}
