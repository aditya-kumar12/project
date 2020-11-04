package com.user.user.Dto;

import com.user.user.bean.User1;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {

 private String userName;
 private String password;
 private String active;
 private List<GrantedAuthority> authorities;


 public MyUserDetails(User1 username){

     this.userName=username.getUsername();
     this.password=username.getPass();
     this.active=username.getActive();
     this.authorities=Arrays.stream(username.getType().split(","))
             .map(SimpleGrantedAuthority::new)
             .collect(Collectors.toList());
     System.out.println(this.authorities);
 }



 public MyUserDetails(){

 }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
