package com.training.training.repository;

import com.training.training.bean.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface Trainingrepository extends JpaRepository<Training, Long>
{


    List<Training> findByUseridAndMentorid(long userid,long mentorid);
}
