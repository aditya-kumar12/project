package com.user.user.controller;

import com.user.user.Dto.AuthenticationRequest;
import com.user.user.Dto.AuthenticationResponse;
import com.user.user.Util.JwtUtil;
import com.user.user.service.MyUserDetailService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value="/login")
public class Authenticationcontroller {

    private static final org.slf4j.Logger logger= LoggerFactory.getLogger(Authenticationcontroller.class);

   @Autowired
   private AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailService myUserDetailService;

    @Autowired
    JwtUtil jwtTokenUtil;


    @PostMapping
   public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
        throws Exception{

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()));

        }catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password",e);

        }

        final UserDetails userDetails=myUserDetailService.loadUserByUsername(authenticationRequest
        .getUsername());

        final String jwt=jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));



    }





}
