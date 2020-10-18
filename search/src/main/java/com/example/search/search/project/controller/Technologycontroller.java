package com.example.search.search.project.controller;


import com.example.search.search.project.bean.Technology;
import com.example.search.search.project.service.Technologyservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/technology")
public class Technologycontroller {

    @Autowired
    Technologyservice technologyservice;

    @GetMapping(value="/get", headers="Accept=application/json")
    public ResponseEntity<List<Technology>> getTechnology(
                                 @RequestParam(defaultValue = "0") Integer pageNo,
                                 @RequestParam(defaultValue = "3") Integer pageSize,
                                 @RequestParam(defaultValue = "id") String sortBy)
    {

        List<Technology> tasks=technologyservice.getTechnology(pageNo, pageSize, sortBy);
        return new ResponseEntity<List<Technology>>(tasks, new HttpHeaders(), HttpStatus.OK);


    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Technology> getTechnologyById(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        Technology technology = technologyservice.findById(id);
        if (technology == null) {
            return new ResponseEntity<Technology>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Technology>(technology, HttpStatus.OK);
    }

//    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Technology> getTechnologyById(@PathVariable("id") long id) {
//        System.out.println("Fetching User with id " + id);
//        Technology technology = technologyservice.findById(id);
//        if (technology == null) {
//            return new ResponseEntity<Technology>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<Technology>(technology, HttpStatus.OK);
//    }

//    @PostMapping(value="/create",headers="Accept=application/json")
//    public ResponseEntity<Void> createUser(@RequestBody Technology technology, UriComponentsBuilder ucBuilder){
//        System.out.println("Creating User "+technology.getName());
//        technologyservice.createTechnology(technology);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(technology.getId()).toUri());
//        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
//    }

    @PostMapping(value="/create",headers="Accept=application/json")
    public ResponseEntity<Object> createUser(@RequestBody Technology technology){
        System.out.println("Creating User "+technology.getName());
        technologyservice.createTechnology(technology);
        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(technology.getId()).toUri());
//        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
        URI path= ServletUriComponentsBuilder.fromCurrentRequest().path("/user/{id}")
                .buildAndExpand(technology.getId()).toUri();

       return ResponseEntity.created(path).build();

    }

    @DeleteMapping(value="/{id}", headers ="Accept=application/json")
    public ResponseEntity<Technology> deleteTechnology(@PathVariable("id") long id){
        Technology technology = technologyservice.findById(id);
        if (technology == null) {
            return new ResponseEntity<Technology>(HttpStatus.NOT_FOUND);
        }
        technologyservice.deleteTechnologyById(id);
        return new ResponseEntity<Technology>(HttpStatus.NO_CONTENT);
    }


    @PutMapping(value="/update", headers="Accept=application/json")
    public ResponseEntity<String> updateUser(@RequestBody Technology current)
    {
        //System.out.println("sd");
        Technology technology = technologyservice.findById(current.getId());
        if (technology==null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        technologyservice.update(current, current.getId());
        return new ResponseEntity<String>(HttpStatus.OK);
    }


    @GetMapping(value="/getskill/{id}", headers="Accept=application/json")
    public String getskill(@PathVariable long id) {
        String tasks=technologyservice.findbyskill(id);
        return tasks;

    }

    @GetMapping(value="/searchskill/{skill}", headers="Accept=application/json")
    public List<Technology> searchskill(@PathVariable String skill) {
        List<Technology> tasks=technologyservice.searchskill(skill);
        return tasks;

    }



}
