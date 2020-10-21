package com.user.user.service;

import com.user.user.bean.User1;
import com.user.user.repository.Userrepository;
//import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Userserviceimp implements Userservice {

    @Autowired
    Userrepository userrepository;



    public User1 findbyid(long id) {
        return userrepository.findById(id).get();
    }


    public List<User1> getUser(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<User1> pagedResult = userrepository.findAll(paging);



        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            //return new ArrayList<Technology>();
            return userrepository.findAll();
        }
    }



    public void createUser(User1 user) {
        userrepository.save(user);

    }


    public void deleteUserById(long id) {
        userrepository.deleteById(id);

    }


    public void update(User1 current, long id) {
        userrepository.save(current);

    }
}
