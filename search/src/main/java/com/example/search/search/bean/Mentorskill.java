package com.example.search.search.bean;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="Mentorskill")
public class Mentorskill implements Serializable {

    @Id
    //@GeneratedValue(strategy= GenerationType.AUTO).identiy
    private long id;

    @Column(name="mentorid")
    private long mid;

    @Column(name="skillid")
    private long sid;

    @Column(name="rating")
    private long rating;

    @Column(name="experience")
    private String experience;

    @Column(name="training_delivered")
    private long tradel;

    @Column(name="facilites_offered")
    private String facoff;




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }

    public long getMid() {
        return mid;
    }

    public long getRating() {
        return rating;
    }

    public String getExperience() {
        return experience;
    }

    public long getTradel() {
        return tradel;
    }

    public String getFacoff() {
        return facoff;
    }

    public void setFacoff(String facoff) {
        this.facoff = facoff;
    }

    public void setTradel(long tradel) {
        this.tradel = tradel;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    public void setMid(long mid) {
        this.mid = mid;
    }


}