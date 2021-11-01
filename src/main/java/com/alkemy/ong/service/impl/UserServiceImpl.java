package com.alkemy.ong.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alkemy.ong.model.Role;
import com.alkemy.ong.model.User;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleServiceImpl roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public User register(User user) {
        Role role = new Role();
        role = roleService.findByName("user");
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreationDate(new Date());
        return userRepo.save(user);
    }

    public List<String> showRegisterErrors(BindingResult results) {
        List<String> errors = new ArrayList<>();
        results.getFieldErrors().stream().forEach(e -> {
            errors.add(e.getDefaultMessage());
        });
        return errors;
    }

}
