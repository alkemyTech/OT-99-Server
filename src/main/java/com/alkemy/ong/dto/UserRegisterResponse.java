package com.alkemy.ong.dto;

import com.alkemy.ong.model.Users;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class UserRegisterResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String jwt;

    public static UserRegisterResponse mapToResponse(Users user) {
        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        userRegisterResponse.setId(user.getId());
        userRegisterResponse.setFirstName(user.getFirstName());
        userRegisterResponse.setLastName(user.getLastName());
        userRegisterResponse.setEmail(user.getEmail());

        return userRegisterResponse;
    }

}
