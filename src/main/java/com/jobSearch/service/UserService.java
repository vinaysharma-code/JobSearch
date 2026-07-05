package com.jobSearch.service;

import com.jobSearch.dto.request.LoginRequest;
import com.jobSearch.dto.request.RegisterRequest;
import com.jobSearch.dto.response.LoginResponse;
import com.jobSearch.dto.response.RegisterResponse;
import com.jobSearch.entity.User;
import com.jobSearch.enums.Role;
import com.jobSearch.exception.DuplicateResourceException;
import com.jobSearch.exception.ResourceNotFoundException;
import com.jobSearch.repository.UserRepo;
import com.jobSearch.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserRepo userRepo ;

    private final PasswordEncoder passwordEncoder ;


    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;
    public UserService(UserRepo userRepo,JwtService jwtService ,AuthenticationManager authenticationManager,PasswordEncoder passwordEncoder) {
        this.userRepo=userRepo;
        this.jwtService=jwtService;
        this.authenticationManager=authenticationManager;
        this.passwordEncoder=passwordEncoder;
    }
    @Transactional
    public RegisterResponse register(RegisterRequest request) {
        if(userRepo.existsByEmail(request.getEmail())){
            throw new DuplicateResourceException("email already exists");
        }
        User user = new User();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        user.setActive(true);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        userRepo.save(user);

        RegisterResponse response = new RegisterResponse();
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
     return response ;
    }

    public LoginResponse login(LoginRequest request) {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
              request.getEmail(), request.getPassword()
      )) ;
        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow (() ->  new ResourceNotFoundException("User not found"));
      String token =   jwtService.generateToken(user);
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
return response ;
    }



}
