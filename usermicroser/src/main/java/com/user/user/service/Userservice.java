package com.user.user.service;

//import org.apache.catalina.User;
import com.user.user.bean.*;

import java.util.List;

public interface Userservice {
    public User1 findbyid(long id);
    public List<User1> getUser(Integer pageNo, Integer pageSize, String sortBy);
    public void createUser(User1 user);
    public void deleteUserById(long id);


    public void update(User1 current, long id);

}