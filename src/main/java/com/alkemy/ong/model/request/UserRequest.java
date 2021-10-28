package com.alkemy.ong.model.request;

import com.alkemy.ong.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserRequest {

    private String firstName;
    private String lastName;
    private String email;
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
