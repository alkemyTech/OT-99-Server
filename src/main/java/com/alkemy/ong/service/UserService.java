package com.alkemy.ong.service;

import com.alkemy.ong.exception.EmailAlreadyExistException;
import com.alkemy.ong.model.User;
import com.alkemy.ong.model.request.UserRegisterRequest;
import com.alkemy.ong.model.response.UserRegisterResponse;

public interface UserService {

    UserRegisterResponse register(UserRegisterRequest userReq) throws EmailAlreadyExistException;

    User findByEmail(String email);

}
