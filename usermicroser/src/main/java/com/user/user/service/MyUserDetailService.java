package com.user.user.service;

import com.user.user.Dto.MyUserDetails;
import com.user.user.bean.User1;
import com.user.user.repository.Userrepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userDetailsService")
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    Userrepository userrepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       // return new MyUserDetails(s);

        System.out.println(username);
        Optional<User1> user= userrepository.findByUserName(username);
        System.out.println(user.get().getUsername());
        user.orElseThrow(()-> new UsernameNotFoundException("not found :"+username));

       return user.map(MyUserDetails::new).get();

    }






}
