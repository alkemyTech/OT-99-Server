package com.alkemy.ong.service;

import java.io.IOException;

import com.alkemy.ong.dto.UserRegisterRequest;
import com.alkemy.ong.dto.UserRegisterResponse;
import com.alkemy.ong.exception.EmailAlreadyExistException;
import com.alkemy.ong.model.Users;
import java.util.List;

public interface UserService {

    UserRegisterResponse register(UserRegisterRequest userReq) throws EmailAlreadyExistException, IOException;

    Users findByEmail(String email);

    List<Users> getAllUsers();

}
