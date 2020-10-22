package com.training.training.repository;

import com.training.training.bean.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface Trainingrepository extends JpaRepository<Training, Long>
{

}
