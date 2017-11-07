package com.brentmennen.Entity;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Meta Data",
        "Time Series (1min)"
})
@Entity
public class Live {
/*
    @GenericGenerator(
            name = "SequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "id", value = "ID_SEQUENCE"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1000"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )*/
    @Id
    //@GeneratedValue(generator = "SequenceGenerator")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID",unique=true,nullable=false, columnDefinition = "serial")
    private Long id;
    @JsonProperty("Meta Data")
    @Embedded
    private MetaData metaData;
    @JsonProperty("Time Series (1min)")
    @Embedded
    private TimeSeries1min timeSeries1min;
    //@JsonIgnore
    //private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("Meta Data")
    public MetaData getMetaData() {
        return metaData;
    }

    @JsonProperty("Meta Data")
    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    @JsonProperty("Time Series (1min)")
    public TimeSeries1min getTimeSeries1min() {
        return timeSeries1min;
    }

    @JsonProperty("Time Series (1min)")
    public void setTimeSeries1min(TimeSeries1min timeSeries1min) {
        this.timeSeries1min = timeSeries1min;
    }

    //@JsonAnyGetter
    //public Map<String, Object> getAdditionalProperties() {
      //  return this.additionalProperties;
    //}

    //@JsonAnySetter
    //public void setAdditionalProperty(String name, Object value) {
      //  this.additionalProperties.put(name, value);
    //}

    public Live() {}

    public Live(MetaData metaData, TimeSeries1min timeSeries1min) {
        super();
        this.metaData = metaData;
        this.timeSeries1min = timeSeries1min;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("metaData", metaData).append("timeSeries1min", timeSeries1min).toString();
    }

}









/*
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.mapping.PrimaryKey;

import javax.persistence.*;


@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Live {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long Id;

    @JsonProperty("Meta Data")
    @Embedded
    private MetaData metaData;

    @JsonProperty("Time Series")
    @Embedded
    private TimeSeries timeSeriesData;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public TimeSeries getTimeSeriesData() {
        return timeSeriesData;
    }

    public void setTimeSeriesData(TimeSeries timeSeriesData) {
        this.timeSeriesData = timeSeriesData;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public Live() {}

    public Live(MetaData metaData, TimeSeries timeSeriesData) {
        this.metaData = metaData;
        this.timeSeriesData = timeSeriesData;
    }

    //@Override
    //public String toString(){
      //  return "Live{" +
        //        "metadata='" + metaData.getSymbol() + '\'' +
          //      ", time=" + timeSeriesData.toString() + '}';
    //}
}
*/