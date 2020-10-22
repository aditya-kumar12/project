package com.training.training.service;

import com.training.training.bean.Training;

import java.util.List;

public interface Trainingservice {
    public Training findById(long id);
    public List<Training> getTraining(Integer pageNo, Integer pageSize, String sortBy);
    public void createTraining(Training training);
    public void deleteTrainingById(long id);
    public void update(Training current, long id);
    //public void approved();
    public Training getcompletedtraining(long uid,long mid);



}
