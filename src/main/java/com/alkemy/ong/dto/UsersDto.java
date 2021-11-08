package com.alkemy.ong.dto;

import com.alkemy.ong.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersDto{
    public String firstName;
    public String lastName;
    public String email;
    public String photo;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public Role role;
    public Date creationDate;
    public Date lastUpdate;
    public Boolean deleted;

}
