package com.brentmennen.Entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetaData{

    @JsonProperty("1. Information")
    private String information;
    @JsonProperty("2. Symbol")
    private String Symbol;
    @JsonProperty("3. Last Refreshed")
    private String refreshed;
    @JsonProperty("4. Interval")
    private String interval;
    @JsonProperty("5. Output Size")
    private String outputSize;
    @JsonProperty("6. Time Zone")
    private String timeZone;

    public MetaData(){}
}

