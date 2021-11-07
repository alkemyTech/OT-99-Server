package com.alkemy.ong.service;

import com.alkemy.ong.dto.JwtTokenDto;
import com.alkemy.ong.dto.UserLoginRequest;
import com.alkemy.ong.dto.UserRegisterRequest;
import com.alkemy.ong.dto.UserRegisterResponse;
import com.alkemy.ong.exception.EmailAlreadyExistException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.Users;

public interface UserService {

    UserRegisterResponse register(UserRegisterRequest userReq) throws EmailAlreadyExistException;

    Users findByEmail(String email);

    void delete(Long id);
    JwtTokenDto authenticate(UserLoginRequest userReq) throws NotFoundException;

}
