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

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {

    UserRegisterResponse register(UserRegisterRequest userReq) throws EmailAlreadyExistException, IOException;

    Users findByEmail(String email);

    void delete(Long id) throws NotFoundException;
    JwtTokenDto authenticate(UserLoginRequest userReq) throws NotFoundException, InvalidCredentialsException;

    List<Users> getAllUsers();

    UserRegisterResponse upgradeUser(Long id, UserRegisterRequest user) throws NotFoundException;

    UserRegisterResponse getUserRegisterBy(String authorizationHeader) throws UsernameNotFoundException;
}
