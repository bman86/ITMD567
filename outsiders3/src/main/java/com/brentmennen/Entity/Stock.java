package com.brentmennen.Entity;

public class Stock {

    private int id;
    private String symbl;
    private String time;

    public Stock(int id, String symbl, String time) {
        this.id = id;
        this.symbl = symbl;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
