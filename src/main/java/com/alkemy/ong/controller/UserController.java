package com.alkemy.ong.controller;

import com.alkemy.ong.dto.UsersDto;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.impl.UserServiceImpl;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/a/users")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    @ResponseBody
    public List<UsersDto> getUsers() {

        List<Users> users = userService.getAllUsers();
        return users.stream().map(this::convertToDto)
                .collect(Collectors.toList());

    }

    private UsersDto convertToDto(Users user) {
        UsersDto userDto = modelMapper.map(user, UsersDto.class);
        return userDto;
    }

}
