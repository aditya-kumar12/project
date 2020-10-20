//package com.example.search.search.controller;
//
////import com.example.search.search.bean.Mentor;
//import com.example.search.search.bean.Mentorcalendar;
//import com.example.search.search.service.Mentorcalendarservice;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/mentorscalendar")
//public class Mentorcalendarcontroller {
//
//   @Autowired
//   Mentorcalendarservice mentorskillservice;
//
//    @GetMapping(value="/get", headers="Accept=application/json")
//    public ResponseEntity<List<Mentorcalendar>> getTechnology(
//            @RequestParam(defaultValue = "0") Integer pageNo,
//            @RequestParam(defaultValue = "3") Integer pageSize,
//            @RequestParam(defaultValue = "id") String sortBy)
//    {
//
//        List<Mentorcalendar> tasks=mentorskillservice.getMentorskills(pageNo, pageSize, sortBy);
//        return new ResponseEntity<List<Mentorcalendar>>(tasks, new HttpHeaders(), HttpStatus.OK);
//
//
//    }
//
//    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Mentorcalendar> getMentorById(@PathVariable("id") long id) {
//        System.out.println("Fetching User with id " + id);
//        Mentorcalendar mentorskill= mentorskillservice.findById(id);
//        if (mentorskill == null) {
//            return new ResponseEntity<Mentorcalendar>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<Mentorcalendar>(mentorskill, HttpStatus.OK);
//    }
//
//
//    @PostMapping(value="/create",headers="Accept=application/json")
//    public ResponseEntity<Void> createMentor(@RequestBody Mentorcalendar mentorskill, UriComponentsBuilder ucBuilder){
//        System.out.println("Creating User "+mentorskill.getId());
//        mentorskillservice.createMentorskills(mentorskill);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(mentorskill.getId()).toUri());
//        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
//    }
//
//    @DeleteMapping(value="/{id}", headers ="Accept=application/json")
//    public ResponseEntity<Mentorcalendar> deleteTechnology(@PathVariable("id") long id){
//        Mentorcalendar mentorskill = mentorskillservice.findById(id);
//        if (mentorskill == null) {
//            return new ResponseEntity<Mentorcalendar>(HttpStatus.NOT_FOUND);
//        }
//        mentorskillservice.deleteMentorskills(id);
//        return new ResponseEntity<Mentorcalendar>(HttpStatus.NO_CONTENT);
//    }
//
//
//    @PutMapping(value="/update", headers="Accept=application/json")
//    public ResponseEntity<String> updateUser(@RequestBody Mentorcalendar current)
//    {
//        //System.out.println("sd");
//        Mentorcalendar mentorskill = mentorskillservice.findById(current.getId());
//        if (mentorskill==null) {
//            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
//        }
//        mentorskillservice.updateMentorskills(current, current.getId());
//        return new ResponseEntity<String>(HttpStatus.OK);
//    }
//
//
//
//}
