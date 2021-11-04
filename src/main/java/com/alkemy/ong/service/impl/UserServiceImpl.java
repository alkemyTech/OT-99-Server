package com.alkemy.ong.service.impl;

import java.util.Date;

import com.alkemy.ong.dto.UserRegisterRequest;
import com.alkemy.ong.dto.UserRegisterResponse;
import com.alkemy.ong.exception.EmailAlreadyExistException;
import com.alkemy.ong.model.Role;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.RoleService;
import com.alkemy.ong.service.UserService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Users findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public UserRegisterResponse register(UserRegisterRequest userReq) throws EmailAlreadyExistException {

        if (this.findByEmail(userReq.getEmail()) != null) {
            throw new EmailAlreadyExistException();
        }

        Users user = UserRegisterRequest.mapToEntity(userReq);
        Role role = new Role();
        role = roleService.findByName("USER");
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreationDate(new Date());

        return UserRegisterResponse.mapToResponse(userRepo.save(user));
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }

}
