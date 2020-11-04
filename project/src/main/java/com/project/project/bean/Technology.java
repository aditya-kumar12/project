package com.project.project.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Technology")
public class Technology {
    @Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @NotNull(message = "name is required")
    @Column(name="name")
    private String name;

    @Column(name="duration")
    private String duration;

    @Column(name="prereq")
    private String prereq;

    @NotNull(message = "toc is required")
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
