package com.alkemy.ong.controller.request;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.alkemy.ong.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserRequest {

    @NotBlank(message = "Name is mandatory")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid", regexp = "[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")
    private String email;

    @NotBlank(message = "Pasword is mandatory")
    @Size(min = 6, max = 16, message = "Password must be between 6 and 16 characters.")
    private String password;

    private String photo;

    public static User mapToEntity(UserRequest req) {
        User u = new User();
        u.setFirstName(req.firstName);
        u.setLastName(req.lastName);
        u.setEmail(req.email);
        u.setPassword(req.password);
        u.setPhoto(req.photo);
        return u;
    }
}

