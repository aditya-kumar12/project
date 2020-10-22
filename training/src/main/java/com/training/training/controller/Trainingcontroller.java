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

import java.util.List;

@RestController
@RequestMapping("/training")
public class Trainingcontroller {

    @Autowired
    Trainingservice trainingservice;


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








}
