package com.brentmennen.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Data
@AllArgsConstructor
@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeSeries {

    @Embedded
    @JsonProperty("2017-10-30 16:00:00")
    private DateTime dateTime;



    public TimeSeries(){}

}


