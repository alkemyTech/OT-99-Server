package com.alkemy.ong.repository;

import com.alkemy.ong.dto.UsersResponse;
import com.alkemy.ong.model.Users;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<Users,Long> {

    public Users findByEmail(String email);
    
    
    @Query("SELECT new com.alkemy.ong.dto.UsersResponse(u.firstName, u.lastName, u.string, u.photo, u.id_role, u.id_role, u.creation_date, u.last_update)FROM Users u")
    List<UsersResponse> listUsers();
}