package com.project.project.service;

import com.project.project.repository.Technologyrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.project.project.bean.Technology;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
//@Transactional
public class Technologyserviceiml implements Technologyservice {
    @Autowired
    Technologyrepository technologyrepository;


    public Technology findById(long id) {
        return technologyrepository.findById(id).get();
    }


    public List<Technology> getTechnology(Integer pageNo, Integer pageSize, String sortBy) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Technology> pagedResult = technologyrepository.findAll(paging);



        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            //return new ArrayList<Technology>();
            return (List<Technology>) technologyrepository.findAll();
        }

    }


    public void createTechnology(Technology technology) {
        technologyrepository.save(technology);

    }


    public void deleteTechnologyById(long id) {
         technologyrepository.deleteById(id);
    }


    public void update(Technology current, long id) {
         technologyrepository.save(current);

    }


    public String findbyskill(long id)
    {
        return technologyrepository.findbyskill(id);
    }


    public List<Technology> searchskill(String skill){
        return (List<Technology>)technologyrepository.searchskill(skill);
    }




}
