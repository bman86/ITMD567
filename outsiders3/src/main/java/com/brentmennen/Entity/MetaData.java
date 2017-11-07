package com.brentmennen.Entity;

        import com.fasterxml.jackson.annotation.*;
        import org.apache.commons.lang3.builder.ToStringBuilder;

        import java.util.HashMap;
        import java.util.Map;

        import javax.persistence.Embeddable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "1. Information",
        "2. Symbol",
        "3. Last Refreshed",
        "4. Interval",
        "5. Output Size",
        "6. Time Zone"
})
@Embeddable
public class MetaData {

    @JsonProperty("1. Information")
    private String _1Information;
    @JsonProperty("2. Symbol")
    private String _2Symbol;
    @JsonProperty("3. Last Refreshed")
    private String _3LastRefreshed;
    @JsonProperty("4. Interval")
    private String _4Interval;
    @JsonProperty("5. Output Size")
    private String _5OutputSize;
    @JsonProperty("6. Time Zone")
    private String _6TimeZone;
    //@JsonIgnore
    //private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("1. Information")
    public String get1Information() {
        return _1Information;
    }

    @JsonProperty("1. Information")
    public void set1Information(String _1Information) {
        this._1Information = _1Information;
    }

    @JsonProperty("2. Symbol")
    public String get2Symbol() {
        return _2Symbol;
    }

    @JsonProperty("2. Symbol")
    public void set2Symbol(String _2Symbol) {
        this._2Symbol = _2Symbol;
    }

    @JsonProperty("3. Last Refreshed")
    public String get3LastRefreshed() {
        return _3LastRefreshed;
    }

    @JsonProperty("3. Last Refreshed")
    public void set3LastRefreshed(String _3LastRefreshed) {
        this._3LastRefreshed = _3LastRefreshed;
    }

    @JsonProperty("4. Interval")
    public String get4Interval() {
        return _4Interval;
    }

    @JsonProperty("4. Interval")
    public void set4Interval(String _4Interval) {
        this._4Interval = _4Interval;
    }

    @JsonProperty("5. Output Size")
    public String get5OutputSize() {
        return _5OutputSize;
    }

    @JsonProperty("5. Output Size")
    public void set5OutputSize(String _5OutputSize) {
        this._5OutputSize = _5OutputSize;
    }

    @JsonProperty("6. Time Zone")
    public String get6TimeZone() {
        return _6TimeZone;
    }

    @JsonProperty("6. Time Zone")
    public void set6TimeZone(String _6TimeZone) {
        this._6TimeZone = _6TimeZone;
    }

   /* @JsonAnyGetter
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
        return new ToStringBuilder(this).append("_1Information", _1Information).append("_2Symbol", _2Symbol).append("_3LastRefreshed", _3LastRefreshed).append("_4Interval", _4Interval).append("_5OutputSize", _5OutputSize).append("_6TimeZone", _6TimeZone).toString();
    }

    public MetaData() {}

    public MetaData(String _1Information, String _2Symbol, String _3LastRefreshed, String _4Interval, String _5OutputSize, String _6TimeZone) {
        super();
        this._1Information = _1Information;
        this._2Symbol = _2Symbol;
        this._3LastRefreshed = _3LastRefreshed;
        this._4Interval = _4Interval;
        this._5OutputSize = _5OutputSize;
        this._6TimeZone = _6TimeZone;
    }
}











/*
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Embeddable;


@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public class MetaData{

    @JsonProperty("1. Information")
    private String information;
    @JsonProperty("2. Symbol")
    private String symbol;
    @JsonProperty("3. Last Refreshed")
    private String refreshed;
    @JsonProperty("4. Interval")
    private String interval;
    @JsonProperty("5. Output Size")
    private String outputSize;
    @JsonProperty("6. Time Zone")
    private String timeZone;

    //all args constructor
    public MetaData(String information, String symbol, String refreshed, String interval, String outputSize, String timeZone) {
        this.information = information;
        this.symbol = symbol;
        this.refreshed = refreshed;
        this.interval = interval;
        this.outputSize = outputSize;
        this.timeZone = timeZone;
    }

    public MetaData() {}

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getRefreshed() {
        return refreshed;
    }

    public void setRefreshed(String refreshed) {
        this.refreshed = refreshed;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public String getOutputSize() {
        return outputSize;
    }

    public void setOutputSize(String outputSize) {
        this.outputSize = outputSize;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
}

*/