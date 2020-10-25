package com.user.user.controller;

import com.user.user.Mentor;
import com.user.user.bean.*;
import com.user.user.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
//@RequestMapping("/user")
public class Usercontroller
{

    @Autowired
    Userservice userservice;

    @GetMapping(value="/get", headers="Accept=application/json")
    public ResponseEntity<List<User1>> getUser(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "3") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {

        List<User1> tasks=userservice.getUser(pageNo, pageSize, sortBy);
        return new ResponseEntity<List<User1>>(tasks, new HttpHeaders(), HttpStatus.OK);


    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User1> getUserById(@PathVariable("id") long id) {
        System.out.println("Fetching User1 with id " + id);
        User1 user1 = userservice.findbyid(id);
        if (user1 == null) {
            return new ResponseEntity<User1>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User1>(user1, HttpStatus.OK);
    }



    @PostMapping(value="/create",headers="Accept=application/json")
    public ResponseEntity<Void> createUser(@RequestBody User1 user1, UriComponentsBuilder ucBuilder){
        System.out.println("Creating User1 "+ user1.getFname());
        userservice.createUser(user1);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user1/{id}").buildAndExpand(user1.getId()).toUri());

        if(user1.getType().equals("mentor")) {

            RestTemplate restTemplate=new RestTemplate();
            final String baseurl = "http://localhost:8995/mentor/create";
            URI uri = null;

            try {
                uri = new URI(baseurl);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            String s=user1.getFname()+"@"+user1.getLname()+"12";
            Mentor mentor=new Mentor(user1.getId(),s);
            HttpHeaders header = new HttpHeaders();

            header.setContentType(MediaType.APPLICATION_JSON);
            //To send an object
            HttpEntity<Mentor> entity=new HttpEntity<>(mentor,header);

            assert uri != null;
            ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);


        }

        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @DeleteMapping(value="/{id}", headers ="Accept=application/json")
    public ResponseEntity<User1> deleteTechnology(@PathVariable("id") long id){
        User1 user1 = userservice.findbyid(id);
        if (user1 == null) {
            return new ResponseEntity<User1>(HttpStatus.NOT_FOUND);
        }
        userservice.deleteUserById(id);
        return new ResponseEntity<User1>(HttpStatus.NO_CONTENT);
    }


    @PutMapping(value="/update", headers="Accept=application/json")
    public ResponseEntity<String> updateUser(@RequestBody User1 current)
    {
        //System.out.println("sd");
        User1 user1 = userservice.findbyid(current.getId());
        if (user1 ==null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        userservice.update(current, current.getId());
        return new ResponseEntity<String>(HttpStatus.OK);
    }








}
