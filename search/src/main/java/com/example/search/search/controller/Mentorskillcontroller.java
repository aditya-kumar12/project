package com.example.search.search.controller;


import com.example.search.search.bean.Mentor;
import com.example.search.search.bean.Mentorskill;
import com.example.search.search.Technology;
import com.example.search.search.service.Mentorservice;
import com.example.search.search.service.Mentorskillservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mentorskill")
public class Mentorskillcontroller {
    @Autowired
    Mentorskillservice mentorskillservice;

    @Autowired
    Mentorservice mentorservice;


    @GetMapping(value="/get", headers="Accept=application/json")
    public ResponseEntity<List<Mentorskill>> getTechnology(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "3") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {

        List<Mentorskill> tasks=mentorskillservice.getMentorskills(pageNo, pageSize, sortBy);
        return new ResponseEntity<List<Mentorskill>>(tasks, new HttpHeaders(), HttpStatus.OK);


    }


    @GetMapping(value="/giveall", headers="Accept=application/json")
    public List<Mentorskill> getAll(){
        List<Mentorskill> task=mentorskillservice.getAllMentorskill();
        return task;
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mentorskill> getMentorById(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        Mentorskill mentorskill= mentorskillservice.findById(id);
        if (mentorskill == null) {
            return new ResponseEntity<Mentorskill>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Mentorskill>(mentorskill, HttpStatus.OK);
    }

//    @GetMapping(value = "/skill/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<Mentorskill> getMentorSkillById(@PathVariable("id") long sid) {
//        System.out.println("Fetching User with id " + sid);
//        List<Mentorskill> mentorskill= mentorskillservice.findBySkillId(sid);
//
//        return mentorskill;
//    }


    @PostMapping(value="/create",headers="Accept=application/json")
    public ResponseEntity<Void> createMentor(@RequestBody Mentorskill mentorskill, UriComponentsBuilder ucBuilder){
        System.out.println("Creating User "+mentorskill.getId());
        mentorskillservice.createMentorskills(mentorskill);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(mentorskill.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @DeleteMapping(value="/{id}", headers ="Accept=application/json")
    public ResponseEntity<Mentorskill> deleteTechnology(@PathVariable("id") long id){
        Mentorskill mentorskill = mentorskillservice.findById(id);
        if (mentorskill == null) {
            return new ResponseEntity<Mentorskill>(HttpStatus.NOT_FOUND);
        }
        mentorskillservice.deleteMentorskills(id);
        return new ResponseEntity<Mentorskill>(HttpStatus.NO_CONTENT);
    }


    @PutMapping(value="/update", headers="Accept=application/json")
    public ResponseEntity<String> updateUser(@RequestBody Mentorskill current)
    {
        //System.out.println("sd");
        Mentorskill mentorskill = mentorskillservice.findById(current.getId());
        if (mentorskill==null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        mentorskillservice.updateMentorskills(current, current.getId());
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @GetMapping(value = "/username/{skill}",headers="Accept=application/json")
    public List<String> getusername(@PathVariable String skill)
    {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:8990/technology/searchskill/";
        URI uri = null;
        try {
            uri = new URI(baseUrl);
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ResponseEntity<Technology[]> result=restTemplate.getForEntity(uri+skill,Technology[].class);


        List<String> list=new ArrayList<>();
        List<Mentorskill> mentorskillList=mentorskillservice.getAllMentorskill();
        if(result.getBody()!=null)
        {
            for(Technology tech:result.getBody()){

                long skillid=tech.getId();

                for(Mentorskill mentorskill:mentorskillList)
                {
                    long a=mentorskill.getSid();
                    if(a==skillid)
                    {
                        list.add(mentorservice.findId(mentorskill.getMid()).getUsername());
                    }
                }

            }
        }
        if(list.size()==0)
        {
            list.add("empty list");
        }
        return list;

    }








}