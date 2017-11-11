package com.brentmennen.Entity;


public class AlphaPojo {

    private Integer id;
    public MetaData metaData;
    public TimeSeries timeSeries;

    public AlphaPojo(){}

    public AlphaPojo(Integer id, MetaData metaData, TimeSeries timeSeries) {
        this.id = id;
        this.metaData = metaData;
        this.timeSeries = timeSeries;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public TimeSeries getTimeSeries() {
        return timeSeries;
    }

    public void setTimeSeries(TimeSeries timeSeries) {
        this.timeSeries = timeSeries;
    }
}
