package com.alkemy.ong.controller;

import javax.validation.Valid;

import com.alkemy.ong.dto.UserRegisterRequest;
import com.alkemy.ong.exception.EmailAlreadyExistException;
import com.alkemy.ong.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegisterRequest userReq) throws EmailAlreadyExistException {
        return new ResponseEntity<>(userService.register(userReq), HttpStatus.CREATED);
    }
}
