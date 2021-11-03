package com.alkemy.ong.service;

import com.alkemy.ong.dto.UserRegisterRequest;
import com.alkemy.ong.dto.UserRegisterResponse;
import com.alkemy.ong.exception.EmailAlreadyExistException;
import com.alkemy.ong.model.User;

public interface UserService {

    UserRegisterResponse register(UserRegisterRequest userReq) throws EmailAlreadyExistException;

    User findByEmail(String email);

}
