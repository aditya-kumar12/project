package com.example.search.search.service;


import com.example.search.search.bean.Mentorskill;
import com.example.search.search.repository.Mentorskillrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class Mentorskillserviceiml implements Mentorskillservice {

    @Autowired
    Mentorskillrepo mentorskillrepo;

    public Mentorskill findById(long id) {
        return mentorskillrepo.findById(id).get();
    }

    @Override
    public List<Mentorskill> getAllMentorskill() {

        return (List<Mentorskill>)mentorskillrepo.findAll();
    }

    public List<Mentorskill> findBySkillId(long sid){
        return (List<Mentorskill>)mentorskillrepo.findBySkillId(sid);
    }

    @Override
    public List<Mentorskill> getMentorskills(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Mentorskill> pagedResult = mentorskillrepo.findAll(paging);


        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            //return new ArrayList<Technology>();
            return (List<Mentorskill>) mentorskillrepo.findAll();
        }

    }


    public void createMentorskills(Mentorskill skill) {
        mentorskillrepo.save(skill);

    }


    public void deleteMentorskills(long id) {
        mentorskillrepo.deleteById(id);
    }


    public void updateMentorskills(Mentorskill current, long id) {
        mentorskillrepo.save(current);

    }

}
