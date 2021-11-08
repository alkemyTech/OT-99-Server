package com.alkemy.ong.service;

import com.alkemy.ong.dto.JwtTokenDto;
import com.alkemy.ong.dto.UserLoginRequest;
import java.io.IOException;

import com.alkemy.ong.dto.UserRegisterRequest;
import com.alkemy.ong.dto.UserRegisterResponse;
import com.alkemy.ong.exception.EmailAlreadyExistException;
import com.alkemy.ong.exception.InvalidCredentialsException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.Users;
import java.util.List;

public interface UserService {

    UserRegisterResponse register(UserRegisterRequest userReq) throws EmailAlreadyExistException, IOException;

    Users findByEmail(String email);

    JwtTokenDto authenticate(UserLoginRequest userReq) throws NotFoundException, InvalidCredentialsException;

    List<Users> getAllUsers();

    UserRegisterResponse upgradeUser(Long id, UserRegisterRequest user) throws NotFoundException;
}
