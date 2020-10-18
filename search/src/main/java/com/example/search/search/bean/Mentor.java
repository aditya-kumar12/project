package com.example.search.search.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Mentor")
public class Mentor {

    @Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(name="username")
    private String username;


    @Column(name="linkedin_url")
    private String lurl;

    @Column(name="regdate_time")
    private String regdate;

    @Column(name="regcode")
    private String regcode;

    @Column(name="experience")
    private String experience;

    @Column(name="active")
    private String active;


    public void setUsername(String username) {
        this.username = username;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setLurl(String  lurl) {
        this.lurl = lurl;
    }

    public void setRegcode(String regcode) {
        this.regcode = regcode;
    }


    public long getId() {
        return id;
    }

    public String getActive() {
        return active;
    }

    public String getExperience() {
        return experience;
    }

    public String getLurl() {
        return lurl;
    }

    public String getRegcode() {
        return regcode;
    }

    public String getRegdate() {
        return regdate;
    }

    public String getUsername() {
        return username;
    }
}
