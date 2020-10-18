package com.example.search.search.service;

import com.example.search.search.bean.Mentor;
import com.example.search.search.repository.Mentorrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Transactional
public class Mentorserviceiml implements Mentorservice {

    @Autowired
    Mentorrepository mentorrepository;

    public Mentor findById(long id) {
        return mentorrepository.findById(id).get();
    }


    public List<Mentor> getMentor(Integer pageNo, Integer pageSize, String sortBy) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Mentor> pagedResult = mentorrepository.findAll(paging);



        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            //return new ArrayList<Technology>();
            return (List<Mentor>) mentorrepository.findAll();
        }

    }


    public void createMentor(Mentor mentor) {
        mentorrepository.save(mentor);

    }


    public void deleteMentor(long id) {
        mentorrepository.deleteById(id);
    }


    public void update(Mentor current, long id) {
        mentorrepository.save(current);

    }



}
