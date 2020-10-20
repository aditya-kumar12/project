package com.example.search.search.controller;

import com.example.search.search.bean.Mentor;
import com.example.search.search.service.Mentorservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/mentor")
public class Mentorcontroller {

    @Autowired
    Mentorservice mentorservice;

    @GetMapping(value="/get", headers="Accept=application/json")
    public ResponseEntity<List<Mentor>> getTechnology(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "3") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {

        List<Mentor> tasks=mentorservice.getMentor(pageNo, pageSize, sortBy);
        return new ResponseEntity<List<Mentor>>(tasks, new HttpHeaders(), HttpStatus.OK);


    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mentor> getMentorById(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        Mentor mentor = mentorservice.findId(id);
        if (mentor == null) {
            return new ResponseEntity<Mentor>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Mentor>(mentor, HttpStatus.OK);
    }


    @PostMapping(value="/create",headers="Accept=application/json")
    public ResponseEntity<Void> createMentor(@RequestBody Mentor mentor, UriComponentsBuilder ucBuilder){
        System.out.println("Creating User "+mentor.getId());
        mentorservice.createMentor(mentor);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(mentor.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @DeleteMapping(value="/{id}", headers ="Accept=application/json")
    public ResponseEntity<Mentor> deleteTechnology(@PathVariable("id") long id){
        Mentor mentor = mentorservice.findId(id);
        if (mentor == null) {
            return new ResponseEntity<Mentor>(HttpStatus.NOT_FOUND);
        }
        mentorservice.deleteMentor(id);
        return new ResponseEntity<Mentor>(HttpStatus.NO_CONTENT);
    }


    @PutMapping(value="/update", headers="Accept=application/json")
    public ResponseEntity<String> updateUser(@RequestBody Mentor current)
    {
        //System.out.println("sd");
        Mentor mentor = mentorservice.findId(current.getId());
        if (mentor==null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        mentorservice.update(current, current.getId());
        return new ResponseEntity<String>(HttpStatus.OK);
    }

}
