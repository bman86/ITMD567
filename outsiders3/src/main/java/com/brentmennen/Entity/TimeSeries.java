package com.brentmennen.Entity;


public class TimeSeries {

    private String open;

    public TimeSeries(String open) {
        this.open = open;
    }

    public TimeSeries(){}

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }
}
