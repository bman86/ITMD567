package com.outsiders.outsidersV2.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String stkSymbl;
    private Boolean own;
    private Double entry;
    private Boolean up;
    private Double target;
    private String time;

    public Post(){

    }

    public Post(String stkSymbl, Boolean own, Double entry, Boolean up, Double target, String time){
        this.stkSymbl = stkSymbl;
    }

    public long getId() {
        return id;
    }

    public String getStkSymbl() {
        return stkSymbl;
    }

    public void setStkSymbl(String stkSymbl) {
        this.stkSymbl = stkSymbl;
    }
    public Boolean getOwn() {
        return own;
    }

    public void setOwn(Boolean own) {
        this.own = own;
    }

    public Double getEntry() {
        return entry;
    }

    public void setEntry(Double entry) {
        this.entry = entry;
    }

    public Boolean getUp() {
        return up;
    }

    public void setUp(Boolean up) {
        this.up = up;
    }

    public Double getTarget() {
        return target;
    }

    public void setTarget(Double target) {
        this.target = target;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
