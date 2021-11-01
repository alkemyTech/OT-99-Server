package com.alkemy.ong.service;

import com.alkemy.ong.model.User;

public interface UserService {

    User register(User user);
    User findByEmail(String email);

}
