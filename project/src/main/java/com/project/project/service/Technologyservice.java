package com.project.project.service;

import com.project.project.bean.Technology;

import java.util.List;

public interface Technologyservice {

    public Technology findById(long id);
    public List<Technology> getTechnology(Integer pageNo, Integer pageSize, String sortBy);
    public void createTechnology(Technology technology);
    public void deleteTechnologyById(long id);


    public void update(Technology current, long id);

    String findbyskill(long id);
    List<Technology> searchskill(String skill);

}
