package com.brentmennen.Entity;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "2017-11-06 16:00:00"
})
@Embeddable
public class TimeSeries1min {

    @JsonProperty("2017-11-06 16:00:00")
    @Embedded
    private DateTime dateTime;
    //@JsonIgnore
    //private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("2017-11-06 16:00:00")
    public DateTime getDateTime() {
        return dateTime;
    }

    @JsonProperty("2017-11-06 16:00:00")
    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }
    /*
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
*/
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("2017-11-06 16:00:00", dateTime).toString();
    }

    public TimeSeries1min(){}

    public TimeSeries1min(DateTime dateTime) {
        super();
        this.dateTime = dateTime;
    }
}