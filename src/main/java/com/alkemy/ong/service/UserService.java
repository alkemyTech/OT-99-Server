package com.alkemy.ong.service;

import com.alkemy.ong.dto.JwtTokenDto;
import com.alkemy.ong.dto.UserLoginRequest;
import java.io.IOException;

import com.alkemy.ong.dto.UserRegisterRequest;
import com.alkemy.ong.dto.UserRegisterResponse;
import com.alkemy.ong.exception.DataAlreadyExistException;
import com.alkemy.ong.exception.InvalidCredentialsException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.Users;
import java.util.List;

public interface UserService {

    UserRegisterResponse register(UserRegisterRequest userReq) throws DataAlreadyExistException, IOException;

    Users findByEmail(String email);

    void delete(Long id) throws NotFoundException;
    JwtTokenDto authenticate(UserLoginRequest userReq) throws NotFoundException, InvalidCredentialsException;

    List<Users> getAllUsers();

    UserRegisterResponse upgradeUser(Long id, UserRegisterRequest user) throws NotFoundException;

    Users getUserLogged() throws NotFoundException;

    


}
