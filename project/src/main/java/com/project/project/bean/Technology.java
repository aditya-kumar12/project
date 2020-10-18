package com.project.project.bean;

import javax.persistence.*;

@Entity
@Table(name="Technology")
public class Technology {
    @Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="duration")
    private String duration;

    @Column(name="prereq")
    private String prereq;

    @Column(name="toc")
    private String toc;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrereq() {
        return prereq;
    }


    public void setPrereq(String prereq) {
        this.prereq = prereq;
    }

    public String getToc() {
        return toc;
    }


    public void setToc(String toc) {
        this.toc = toc;
    }
}
