package com.brentmennen.Entity;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashMap;

@AllArgsConstructor
@MappedSuperclass
@JsonIgnoreProperties(ignoreUnknown = true)
public class Live<T extends TimeSeries> implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    @JsonProperty("Meta Data")
    private MetaData metaData;

    @JsonProperty("Time Series")
    private HashMap<Date, T> timeSeriesData;

    public HashMap<Date, T> getTimeSeriesData() {
        return timeSeriesData;
    }

    public void setTimeSeriesData(HashMap<Date, T> timeSeriesData) {
        this.timeSeriesData = timeSeriesData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public Live() {}

    @Override
    public String toString(){
        return "Live{" +
                "metadata='" + metaData.getSymbol() + '\'' +
                ", time=" + timeSeriesData.toString() + '}';
    }
}
