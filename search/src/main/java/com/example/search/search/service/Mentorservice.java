package com.example.search.search.service;

import com.example.search.search.bean.Mentor;

import java.util.List;

public interface Mentorservice {
    public Mentor findById(long id);
    public List<Mentor> getMentor(Integer pageNo, Integer pageSize, String sortBy);
    public void createMentor(Mentor training);
    public void deleteMentor(long id);
    public void update(Mentor current, long id);
}
