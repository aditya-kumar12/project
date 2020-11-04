package com.training.training.controller;

import com.training.training.Payment;
import com.training.training.Payment;
import com.training.training.bean.Training;
import com.training.training.service.Trainingservice;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
//@RequestMapping("/training")
public class Trainingcontroller {

    private static final org.slf4j.Logger logger= LoggerFactory.getLogger(Trainingcontroller.class);


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
        logger.info("Get items of training of page no "+pageNo);
        List<Training> tasks=trainingservice.getTraining(pageNo, pageSize, sortBy);
        return new ResponseEntity<List<Training>>(tasks, new HttpHeaders(), HttpStatus.OK);


    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Training> getTechnologyById(@PathVariable("id") long id) {
        logger.info("Fetching User with id " + id);
        Training training = trainingservice.findById(id);
        if (training == null) {
            return new ResponseEntity<Training>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Training>(training, HttpStatus.OK);
    }


    @PostMapping(value="/create",headers="Accept=application/json")
    public ResponseEntity<Void> createUser(@RequestBody Training training, UriComponentsBuilder ucBuilder){
        logger.info("Creating training of id: "+training.getId());
        trainingservice.createTraining(training);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(training.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @DeleteMapping(value="delete/{id}", headers ="Accept=application/json")
    public ResponseEntity<Training> deleteTechnology(@PathVariable("id") long id){
        Training training = trainingservice.findById(id);
        logger.info("Delete training with id: "+id);
        if (training == null) {
            return new ResponseEntity<Training>(HttpStatus.NOT_FOUND);
        }
        trainingservice.deleteTrainingById(id);
        return new ResponseEntity<Training>(HttpStatus.NO_CONTENT);
    }


    @PutMapping(value="/update", headers="Accept=application/json")
    public ResponseEntity<String> updateUser(@RequestBody Training current)
    {
        logger.info("Updated training with id: "+current.getId());
        Training training = trainingservice.findById(current.getId());
        if (training==null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        trainingservice.update(current, current.getId());
        return new ResponseEntity<String>(HttpStatus.OK);
    }

//propose a training
    @PostMapping(value="/propose/{learn}/{mentor}/{skill}",headers="Accept=application/json")
    public String propose(@RequestBody Training training,@PathVariable Long learn,@PathVariable Long mentor,@PathVariable Long skill){
        logger.info("Creating user "+training.getId());
        training.setMentorid(mentor);
        training.setUserid(learn);
        training.setTechid(skill);
        training.setStatus("proposed");

        trainingservice.createTraining(training);
        return "training proposed";

    }

    //approve via id(primarykey)
    @PutMapping(value="/approve/{id}", headers="Accept=application/json")
    public ResponseEntity<String> approve(@PathVariable long id)
    {
        logger.info("approve training of id: " +id);
        Training training = trainingservice.findById(id);
        if (training==null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        training.setStatus("approved");
        trainingservice.update(training, training.getId());
        return new ResponseEntity<String>(HttpStatus.OK);
    }
//finalize via id
    @PutMapping(value="/finalize/{id}", headers="Accept=application/json")
    public ResponseEntity<String> finalize(@PathVariable long id)
    {
        logger.info("finalize training of id:"+ id);
        Training training = trainingservice.findById(id);
        if (training==null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        training.setStatus("training started");
        training.setProgress("0%");
        training.setAmountreceived("nothing");
        training.setStartdate("1/11/2020");
        training.setEnddate("04/03/2020");
        training.setStarttime("10am");
        training.setEndtime("2pm");
        trainingservice.update(training, training.getId());
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @PutMapping(value="/complete/{id}", headers="Accept=application/json")
    public ResponseEntity<String> complete(@PathVariable long id)
    {
        logger.info("complete training of id:"+ id);
        Training training = trainingservice.findById(id);
        if (training==null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        training.setStatus("completed");
        training.setProgress("100%");
        training.setAmountreceived("45000");
        dopayment(training.getId(),training.getUserid(),training.getMentorid(),training.getTechid(),"45000","good");
        trainingservice.update(training, training.getId());
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    //list of completed training of uid and mid
    @GetMapping(value = "complete/{uid}/{mid}", headers="Accept=application/json")
    public List<Training> completed(@PathVariable("uid") long uid,@PathVariable("mid") long mid) {
        System.out.println("Fetching User with id " + uid+" "+mid);
        List<Training> training = trainingservice.getcompletedtraining(uid,mid);
        System.out.println(training);
        List<Training> op=new ArrayList<Training>();
        if (training == null) {
            logger.info("either not comleted or inputs are wrong");
           return op;
        }

        for(Training obj:training)
        {
             if(obj.getStatus().equals("completed")){
               logger.info(obj.getStatus());
                 op.add(obj);
           }
        }

        return op ;
    }

    //all progress course details via mid
    @GetMapping(value = "mentorprogress/{id}", headers="Accept=application/json")
    public List<Training> getmentorprogress(@PathVariable("id") long id) {
        logger.info("Fetching User with id " + id);
        List<Training> training = trainingservice.getmentor(id);
        List<Training> op = new ArrayList<Training>();
        if (training == null) {
            return op;
        }


        for(Training obj:training)
        {
            if(!(obj.getStatus().equals("completed"))){
                op.add(obj);
            }
        }

        return op;

    }

    //all progress course details via uid
    @GetMapping(value = "userprogress/{id}", headers="Accept=application/json")
    public List<Training> getuserprogress(@PathVariable("id") long id) {
        logger.info("Fetching User with id " + id);
        List<Training> training = trainingservice.getuser(id);
        List<Training> op = new ArrayList<Training>();
        if (training == null) {
            return op;
        }


        for(Training obj:training)
        {
            if(!(obj.getStatus().equals("completed"))){
                op.add(obj);
            }
        }

        return op;

    }


    public void dopayment(long id,long userid,long mid,long techid,String amount,String rem){
        logger.info("processing paymentof training id:"+id);

        RestTemplate restTemplate=new RestTemplate();


        final String baseurl="http://localhost:8997/createpayment";
        URI uri = null;

        try {
            uri = new URI(baseurl);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        Payment payment=new Payment(Long.toString(id),Long.toString(userid),
                Long.toString(mid),Long.toString(techid),amount,rem);

        HttpHeaders header = new HttpHeaders();

        header.setContentType(MediaType.APPLICATION_JSON);
        //To send an object
        HttpEntity<Payment> entity=new HttpEntity<>(payment,header);

        assert uri != null;
        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);




    }




}
