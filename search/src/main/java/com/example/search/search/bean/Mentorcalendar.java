package com.example.search.search.bean;
import com.fasterxml.jackson.annotation.JsonFormat;


import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.validation.constraints.Past;
import java.time.LocalDate;

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

//    @Column(name="startdate")
//    private String sdate;

    @Past
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name="startdate")
    private LocalDate startdate;


//    @Column(name="enddate")
//    private String enddate;

    @Past
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name="enddate")
    private LocalDate enddate;

    public void setId(long id) {
        this.id = id;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }



    public void setMenid(long menid) {
        this.menid = menid;
    }



    public long getId() {
        return id;
    }



    public String getEndtime() {
        return endtime;
    }

    public long getMenid() {
        return menid;
    }

    public LocalDate getEnddate() {
        return enddate;
    }

    public void setEnddate(LocalDate enddate) {
        this.enddate = enddate;
    }

    public LocalDate getStartdate() {

        return startdate;
    }

    public void setStartdate(LocalDate startdate) {
        this.startdate = startdate;
    }

    public String getStarttime() {
        return starttime;
    }
}
