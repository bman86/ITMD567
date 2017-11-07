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
        "1. open",
        "2. high",
        "3. low",
        "4. close",
        "5. volume"
})
@Embeddable
public class DateTime {

    @JsonProperty("1. open")
    private String _1Open;
    @JsonProperty("2. high")
    private String _2High;
    @JsonProperty("3. low")
    private String _3Low;
    @JsonProperty("4. close")
    private String _4Close;
    @JsonProperty("5. volume")
    private String _5Volume;
    //@JsonIgnore
    //private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("1. open")
    public String get1Open() {
        return _1Open;
    }

    @JsonProperty("1. open")
    public void set1Open(String _1Open) {
        this._1Open = _1Open;
    }

    @JsonProperty("2. high")
    public String get2High() {
        return _2High;
    }

    @JsonProperty("2. high")
    public void set2High(String _2High) {
        this._2High = _2High;
    }

    @JsonProperty("3. low")
    public String get3Low() {
        return _3Low;
    }

    @JsonProperty("3. low")
    public void set3Low(String _3Low) {
        this._3Low = _3Low;
    }

    @JsonProperty("4. close")
    public String get4Close() {
        return _4Close;
    }

    @JsonProperty("4. close")
    public void set4Close(String _4Close) {
        this._4Close = _4Close;
    }

    @JsonProperty("5. volume")
    public String get5Volume() {
        return _5Volume;
    }

    @JsonProperty("5. volume")
    public void set5Volume(String _5Volume) {
        this._5Volume = _5Volume;
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
        return new ToStringBuilder(this).append("_1Open", _1Open).append("_2High", _2High).append("_3Low", _3Low).append("_4Close", _4Close).append("_5Volume", _5Volume).toString();
    }

    public DateTime(){}

    public DateTime(String _1Open, String _2High, String _3Low, String _4Close, String _5Volume) {
        super();
        this._1Open = _1Open;
        this._2High = _2High;
        this._3Low = _3Low;
        this._4Close = _4Close;
        this._5Volume = _5Volume;
    }
}