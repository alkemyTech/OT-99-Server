package com.alkemy.ong.controller;

import com.alkemy.ong.dto.UserRegisterRequest;
import com.alkemy.ong.dto.UsersDto;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
	
    @Autowired
    private UserService userService;
  
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) throws NotFoundException {
        userService.delete(id);
        return new ResponseEntity<>("El usuario fue eliminado con exito",HttpStatus.OK);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<UsersDto>> getUsers() {
       
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> upgradeUser(@PathVariable Long id,@Valid @RequestBody UserRegisterRequest user) throws NotFoundException {
        return new ResponseEntity<>(userService.upgradeUser(id,user),HttpStatus.OK);
    }
}