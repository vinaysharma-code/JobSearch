package com.jobSearch.controller;

import com.jobSearch.dto.request.LoginRequest;
import com.jobSearch.dto.request.RegisterRequest;
import com.jobSearch.dto.response.LoginResponse;
import com.jobSearch.dto.response.RegisterResponse;
import com.jobSearch.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
    RegisterResponse response =  userService.register(request);
      return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping("/login")
    public ResponseEntity<?>  login(@Valid  @RequestBody LoginRequest request) {
        LoginResponse login = userService.login(request);

        return ResponseEntity.status(HttpStatus.OK).body(login);
    }

}
