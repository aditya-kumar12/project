package com.user.user.repository;

//import org.apache.catalina.User;
import com.user.user.bean.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface Userrepository extends JpaRepository<User1, Long> {

    @Query(value="SELECT * FROM user1 a WHERE a.username=?1 ",nativeQuery = true)
    Optional<User1> findByUserName(String username);

}
