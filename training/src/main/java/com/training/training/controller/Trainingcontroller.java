package com.training.training.controller;

import com.training.training.bean.Training;
import com.training.training.service.Trainingservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/training")
public class Trainingcontroller {

    @Autowired
    Trainingservice trainingservice;

//    @Autowired
//    Training training;


    @GetMapping(value="/get", headers="Accept=application/json")
    public ResponseEntity<List<Training>> getTechnology(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "3") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {

        List<Training> tasks=trainingservice.getTraining(pageNo, pageSize, sortBy);
        return new ResponseEntity<List<Training>>(tasks, new HttpHeaders(), HttpStatus.OK);


    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Training> getTechnologyById(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        Training training = trainingservice.findById(id);
        if (training == null) {
            return new ResponseEntity<Training>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Training>(training, HttpStatus.OK);
    }


    @PostMapping(value="/create",headers="Accept=application/json")
    public ResponseEntity<Void> createUser(@RequestBody Training training, UriComponentsBuilder ucBuilder){
        System.out.println("Creating User "+training.getId());
        trainingservice.createTraining(training);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(training.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @DeleteMapping(value="/{id}", headers ="Accept=application/json")
    public ResponseEntity<Training> deleteTechnology(@PathVariable("id") long id){
        Training training = trainingservice.findById(id);
        if (training == null) {
            return new ResponseEntity<Training>(HttpStatus.NOT_FOUND);
        }
        trainingservice.deleteTrainingById(id);
        return new ResponseEntity<Training>(HttpStatus.NO_CONTENT);
    }


    @PutMapping(value="/update", headers="Accept=application/json")
    public ResponseEntity<String> updateUser(@RequestBody Training current)
    {
        //System.out.println("sd");
        Training training = trainingservice.findById(current.getId());
        if (training==null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        trainingservice.update(current, current.getId());
        return new ResponseEntity<String>(HttpStatus.OK);
    }


    @PostMapping(value="/propose/{learn}/{mentor}/{skill}",headers="Accept=application/json")
    public String propose(@RequestBody Training training,@PathVariable Long learn,@PathVariable Long mentor,@PathVariable Long skill){
        System.out.println("Creating User "+training.getId());
        training.setMentorid(mentor);
        training.setUserid(learn);
        training.setTechid(skill);
        training.setStatus("proposed");

        trainingservice.createTraining(training);
        return "training proposed";

    }

    @PutMapping(value="/approve/{id}", headers="Accept=application/json")
    public ResponseEntity<String> approve(@PathVariable long id)
    {
        //System.out.println("sd");
        Training training = trainingservice.findById(id);
        if (training==null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        training.setStatus("approved");
        trainingservice.update(training, training.getId());
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @PutMapping(value="/finalize/{id}", headers="Accept=application/json")
    public ResponseEntity<String> finalize(@PathVariable long id)
    {
        //System.out.println("sd");
        Training training = trainingservice.findById(id);
        if (training==null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        training.setStatus("training started");
        training.setProgress("0%");
        training.setAmountreceived("full");
        training.setStartdate("today");
        training.setEnddate("8 weeks");
        training.setStarttime("10am");
        training.setEndtime("2pm");
        trainingservice.update(training, training.getId());
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @GetMapping(value = "complete/{uid}/{mid}", headers="Accept=application/json")
    public List<Training> completed(@PathVariable("uid") long uid,@PathVariable("mid") long mid) {
        System.out.println("Fetching User with id " + uid+" "+mid);
        List<Training> training = trainingservice.getcompletedtraining(uid,mid);
        System.out.println(training);
        List<Training> op=new ArrayList<Training>();
        if (training == null) {
            System.out.println("either not comleted or inputs are wrong");
           return op;
        }

        for(Training obj:training)
        {
             if(obj.getStatus().equals("completed")){
                 op.add(obj);
           }
        }

        return training ;
    }

    @GetMapping(value = "mentorprogress/{id}", headers="Accept=application/json")
    public List<Training> getmentorprogress(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        List<Training> training = trainingservice.getmentor(id);
        List<Training> op = new ArrayList<Training>();
        if (training == null) {
            return op;
        }


        for(Training obj:training)
        {
            if(obj.getStatus()!="completed"){
                op.add(obj);
            }
        }

        return op;

    }


    @GetMapping(value = "userprogress/{id}", headers="Accept=application/json")
    public List<Training> getuserprogress(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        List<Training> training = trainingservice.getuser(id);
        List<Training> op = new ArrayList<Training>();
        if (training == null) {
            return op;
        }


        for(Training obj:training)
        {
            if(obj.getStatus()!="completed"){
                op.add(obj);
            }
        }

        return op;

    }





}
