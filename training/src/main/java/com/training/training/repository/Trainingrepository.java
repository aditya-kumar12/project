package com.training.training.repository;

import com.training.training.bean.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface Trainingrepository extends JpaRepository<Training, Long>
{

    @Query(value="SELECT * FROM training a WHERE a.userid=?1 AND a.mentorid=?2 ",nativeQuery = true)
     List<Training> uidmid(long userid,long mentorid);

    List<Training> findByMentorid(long id);
    List<Training> findByUserid(long id);

}
