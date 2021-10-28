package com.alkemy.ong.service;

import java.util.Date;

import com.alkemy.ong.model.Role;
import com.alkemy.ong.model.User;
import com.alkemy.ong.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public User register(User user) {
        Role role = new Role();
        role = roleService.findByName("USER");
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreationDate(new Date());
        return userRepo.save(user);
    }
    
}
