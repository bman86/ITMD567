package com.brentmennen.Entity;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Live {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    @Embedded
    @JsonProperty("Meta Data")
    private MetaData metaData;

    @Embedded
    @JsonProperty("Time Series (1min)")
    private TimeSeries timeSeries;


    public Live(){}

}
