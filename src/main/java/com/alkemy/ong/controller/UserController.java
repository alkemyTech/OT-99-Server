package com.alkemy.ong.controller;

import com.alkemy.ong.dto.UserRegisterRequest;
import com.alkemy.ong.dto.UsersDto;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.UserMapper;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.UserService;
import com.alkemy.ong.service.impl.UserServiceImpl;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<UsersDto>> getUsers() {

        List<Users> users = userService.getAllUsers();

        List<UsersDto> usersDto = users.stream().map(userMapper::convertToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(usersDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> upgradeUser(@PathVariable Long id,@Valid @RequestBody UserRegisterRequest user) throws NotFoundException {
        return new ResponseEntity<>(userService.upgradeUser(id,user),HttpStatus.OK);
    }


}
