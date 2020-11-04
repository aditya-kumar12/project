package com.project.project.repository;

import com.project.project.bean.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Technologyrepository extends JpaRepository<Technology,Long>
{

    @Query(value = "SELECT a.name FROM technology a WHERE a.id=?1",nativeQuery = true)
    String findbyskill(long id);

    @Query(value="SELECT * FROM technology a WHERE a.name=?1 ",nativeQuery = true)
    List<Technology> searchskill(String skill);


}