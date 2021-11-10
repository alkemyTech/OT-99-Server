package com.alkemy.ong.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityRequest {

    @NotBlank(message = "Name can't be blank")
    public String name;
    @NotBlank(message = "Content can't be blank")
    @Size(max = 65535, message = "Content can be up to 65535 characters long")
    public String content;
    @NotBlank(message = "Must provide an image")
    public String image;
    public LocalDateTime updateDateTime;
}
