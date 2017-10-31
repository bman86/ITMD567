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
public class DateTime {

    @JsonProperty("1. open ")
    private Double open;

    @JsonProperty("2. high")
    private Double high;

    @JsonProperty("3. low")
    private Double low;

    @JsonProperty("4. close")
    private Double close;

    @JsonProperty("5. volume")
    private Double volume;

    public DateTime() {}


}
