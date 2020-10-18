package com.example.search.search.service;

//import com.example.search.search.bean.Mentorcalendar;


import com.example.search.search.bean.Mentorskill;

import java.util.List;

public interface Mentorskillservice {
    public Mentorskill findById(long id);
    public List<Mentorskill> getAllMentorskill();
    public List<Mentorskill> getMentorskills(Integer pageNo, Integer pageSize, String sortBy);
    public void createMentorskills(Mentorskill training);
    public void deleteMentorskills(long id);
    public void updateMentorskills(Mentorskill current, long id);

    public List<Mentorskill> findBySkillId(long sid);

}
