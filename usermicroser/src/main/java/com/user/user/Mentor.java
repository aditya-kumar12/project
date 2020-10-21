package com.user.user;

import javax.persistence.Column;

public class Mentor {

    private long id;


    private String username;

    private String lurl;


    private String regdate;


    private String regcode;


    private String experience;


    private String active;

    public Mentor(long id,String username){
        this.id=id;
        this.username=username;
        this.active="yes";
        this.regcode="QWM5"+id;
        this.experience="2yr";
        this.regdate="27/7/2018";


    }

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
