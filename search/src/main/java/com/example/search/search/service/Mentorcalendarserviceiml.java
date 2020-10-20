//package com.example.search.search.service;
//
//
//import com.example.search.search.bean.Mentorcalendar;
//import com.example.search.search.repository.Mentorcalendarrepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class Mentorcalendarserviceiml implements Mentorcalendarservice {
//    @Autowired
//    Mentorcalendarrepo mentorskillrepo;
//
//    public Mentorcalendar findById(long id) {
//        return mentorskillrepo.findById(id).get();
//    }
//
//    @Override
//    public List<Mentorcalendar> getMentorskills(Integer pageNo, Integer pageSize, String sortBy) {
//        org.springframework.data.domain.Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
//        Page<Mentorcalendar> pagedResult = mentorskillrepo.findAll(paging);
//
//
//        if(pagedResult.hasContent()) {
//            return pagedResult.getContent();
//        } else {
//            //return new ArrayList<Technology>();
//            return (List<Mentorcalendar>) mentorskillrepo.findAll();
//        }
//
//    }
//
//
//    public void createMentorskills(Mentorcalendar skill) {
//        mentorskillrepo.save(skill);
//
//    }
//
//
//    public void deleteMentorskills(long id) {
//        mentorskillrepo.deleteById(id);
//    }
//
//
//    public void updateMentorskills(Mentorcalendar current, long id) {
//        mentorskillrepo.save(current);
//
//    }
//}
