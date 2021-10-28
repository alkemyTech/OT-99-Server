package com.alkemy.ong.repository;

import com.alkemy.ong.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Long> {

    public Users findByUsername(String username);
}