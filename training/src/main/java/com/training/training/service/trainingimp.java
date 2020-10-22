package com.training.training.service;

import com.training.training.bean.Training;
import com.training.training.repository.Trainingrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class trainingimp implements Trainingservice{

    @Autowired
    Trainingrepository trainingrepository;



    public Training findById(long id) {
        return trainingrepository.findById(id).get();
    }


    public List<Training> getTraining(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Training> pagedResult = trainingrepository.findAll(paging);



        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            //return new ArrayList<Technology>();
            return (List<Training>) trainingrepository.findAll();
        }

    }


    public void createTraining(Training training) {
        trainingrepository.save(training);

    }


    public void deleteTrainingById(long id) {
        trainingrepository.deleteById(id);

    }


    @Override
    public void update(Training current, long id) {
        trainingrepository.save(current);
    }
}
