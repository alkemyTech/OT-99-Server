package com.alkemy.ong.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationRequest {

    @NotBlank(message = "Name can't be blank")
    public String name;
    @NotBlank (message = "Must provide an image")
    public String image;
    public String address;
    public int phone;
    @NotBlank (message = "Must privide an email")
    @Email (message = "Must provide a valid email")
    public String email;
    @NotNull (message = "Must give a welcome text")
    @Size( max = 65535, message = "Message can be up to 65535 characters long")
    public String welcomeText;
    @Size( max = 65535, message = "Message can be up to 65535 characters long")
    public String aboutUsText;
    private LocalDateTime creationDateTime;
    public LocalDateTime updateDateTime;
}
