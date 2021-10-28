package com.alkemy.ong.service;

import com.alkemy.ong.model.Role;
import com.alkemy.ong.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    
    @Autowired
    RoleRepository roleRepo;

    public Role findByName(String name) {
        return roleRepo.findByName(name);
    }
}
