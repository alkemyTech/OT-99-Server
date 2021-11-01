package com.alkemy.ong.controller;

import com.alkemy.ong.model.User;
import com.alkemy.ong.service.impl.UserServiceImpl;
import com.alkemy.ong.controller.request.UserRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Validated @RequestBody UserRequest userReq, BindingResult results) {

        if (results.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userService.showRegisterErrors(results));
        } else if (userService.findByEmail(userReq.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("There is already an account with the email adress: " + userReq.getEmail());
        } else {
            User u = userService.register(UserRequest.mapToEntity(userReq));
            return ResponseEntity.status(HttpStatus.CREATED).body("User " + u.getEmail() + " generated with succes.");
        }
    }
}
