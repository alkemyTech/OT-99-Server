package com.alkemy.ong.controller;

import com.alkemy.ong.model.User;
import com.alkemy.ong.model.request.UserRequest;
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
    public ResponseEntity<?> registerUser(@RequestBody UserRequest userReq) {

        if (userReq.getFirstName() == null || userReq.getLastName() == null || userReq.getEmail() == null
                || userReq.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("firstname, lastname, password and email are required.");
        } else if (userService.findByEmail(userReq.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("There is an account with that email adress:" + userReq.getEmail());
        } else {
            User u = userService.register(UserRequest.mapToEntity(userReq));
            return ResponseEntity.status(HttpStatus.OK).body("User " + u.getEmail() + " generated with succes.");

        }
    }

}
