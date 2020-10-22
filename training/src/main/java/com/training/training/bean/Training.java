package com.training.training.bean;
import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Training")
public class Training {

    @Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(name="userid")
    private long userid;

    @Column(name="mentorid")
    private long mentorid;

    @Column(name="techid")
    private long techid;


    @Column(name="status")
    private String status;

    @Column(name="progress")
    private String progress;

    @Column(name="rating")
    private String rating;

    @Column(name="startdate")
    private String startdate;

    @Column(name="enddate")
    private String enddate;

    @Column(name="starttime")
    private String starttime;

    @Column(name="endtime")
    private String endtime;


    @Column(name="amountreceived")
    private String amountreceived;


    public long getId() {
        return id;
    }

    public long getMentorid() {
        return mentorid;
    }

    public long getTechid() {
        return techid;
    }


    public long getUserid() {
        return userid;
    }

    public String getAmountreceived() {
        return amountreceived;
    }

    public String getEnddate() {
        return enddate;
    }

    public String getEndtime() {
        return endtime;
    }

    public String getProgress() {
        return progress;
    }

    public String getRating() {
        return rating;
    }

    public String getStartdate() {
        return startdate;
    }

    public String getStarttime() {
        return starttime;
    }

    public String getStatus() {
        return status;
    }


    public void setId(long id) {
        this.id = id;
    }

    public void setAmountreceived(String amountreceived) {
        this.amountreceived = amountreceived;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public void setMentorid(long mentorid) {
        this.mentorid = mentorid;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTechid(long techid) {
        this.techid = techid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

}
