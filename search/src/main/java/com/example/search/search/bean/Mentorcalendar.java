package com.example.search.search.bean;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Entity
@Table(name="Mentorcalendar")
public class Mentorcalendar {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(name="mentorid")
    private long menid;

    @Column(name="starttime")
    private String starttime;

    @Column(name="endtime")
    private String endtime;

    @Column(name="startdate")
    private String sdate;

    @Column(name="enddate")
    private String enddate;

    public void setId(long id) {
        this.id = id;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public void setMenid(long menid) {
        this.menid = menid;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public long getId() {
        return id;
    }

    public String getEnddate() {
        return enddate;
    }

    public String getEndtime() {
        return endtime;
    }

    public long getMenid() {
        return menid;
    }

    public String getSdate() {
        return sdate;
    }

    public String getStarttime() {
        return starttime;
    }
}
