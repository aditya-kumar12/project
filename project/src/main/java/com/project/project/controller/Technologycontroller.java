package com.project.project.controller;


import com.project.project.bean.Technology;
import com.project.project.service.Technologyservice;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
//@RequestMapping("/technology")
public class Technologycontroller {

    private static final org.slf4j.Logger logger= LoggerFactory.getLogger(Technologycontroller.class);


    @Autowired
    Technologyservice technologyservice;

    @GetMapping(value="/get", headers="Accept=application/json")
    public ResponseEntity<List<Technology>> getTechnology(
                                 @RequestParam(defaultValue = "0") Integer pageNo,
                                 @RequestParam(defaultValue = "3") Integer pageSize,
                                 @RequestParam(defaultValue = "id") String sortBy)
    {

        List<Technology> tasks=technologyservice.getTechnology(pageNo, pageSize, sortBy);
        logger.info("Get items of page no "+pageNo);
        return new ResponseEntity<List<Technology>>(tasks, new HttpHeaders(), HttpStatus.OK);


    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Technology> getTechnologyById(@PathVariable("id") long id) {
        logger.info("Fetching Technology with id " + id);
        Technology technology = technologyservice.findById(id);
        if (technology == null) {
            return new ResponseEntity<Technology>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Technology>(technology, HttpStatus.OK);
    }


    @PostMapping(value="/create",headers="Accept=application/json")
    public ResponseEntity<Object> createUser(@RequestBody Technology technology){
        logger.info("Creating Technology of "+technology.getName());
        technologyservice.createTechnology(technology);
        HttpHeaders headers = new HttpHeaders();

        URI path= ServletUriComponentsBuilder.fromCurrentRequest().path("/user/{id}")
                .buildAndExpand(technology.getId()).toUri();

       return ResponseEntity.created(path).build();

    }

    @DeleteMapping(value="delete/{id}", headers ="Accept=application/json")
    public ResponseEntity<Technology> deleteTechnology(@PathVariable("id") long id){
        logger.info("Delete skill with id: "+id);
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
        logger.info("Updated skill with id: "+current.getId());
        Technology technology = technologyservice.findById(current.getId());
        if (technology==null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        technologyservice.update(current, current.getId());
        return new ResponseEntity<String>(HttpStatus.OK);
    }


    @GetMapping(value="/getskill/{id}", headers="Accept=application/json")
    public String getskill(@PathVariable long id) {
        logger.info("Get skill name with this id: "+id);
        String task=technologyservice.findbyskill(id);
        if(task==null){
            task="no skill of this id is present";
        }
        return task;

    }

    @GetMapping(value="/searchskill/{skill}", headers="Accept=application/json")
    public List<Technology> searchskill(@PathVariable String skill) {
        logger.info("Get list of this: "+skill);
        List<Technology> tasks=technologyservice.searchskill(skill);

        return tasks;

    }



}
