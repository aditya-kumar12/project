package com.example.search.search.repository;

//import com.example.search.search.bean.Mentor;
import com.example.search.search.bean.Mentorskill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Mentorskillrepo extends JpaRepository<Mentorskill, Long> {

    @Query(value = "SELECT * FROM mentorskill a WHERE a.skillid=?1 ", nativeQuery = true)
    List<Mentorskill> findBySkillId(Long id);



}