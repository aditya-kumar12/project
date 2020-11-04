package com.user.user.bean;

import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name="User1")
public class User1 {
    @Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String pass;

    @Column(name="firstname")
    private String fname;

    @Column(name="lastname")
    private String lname;

    @Column(name="contact")
    private String contact;

    @Column(name="regdate_time")
    private String regdate;

    @Column(name="regid")
    private long regid;

    @Column(name="active")
    private String active;

    @Column(name="type")
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getActive() {
        return active;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContact() {
        return contact;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFname() {
        return fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getLname() {
        return lname;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPass() {
        return pass;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegid(long regid) {
        this.regid = regid;
    }

    public long getRegid() {
        return regid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }



}
