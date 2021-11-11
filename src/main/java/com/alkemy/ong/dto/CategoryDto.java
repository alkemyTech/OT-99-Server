package com.alkemy.ong.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.alkemy.ong.model.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CategoryDto {

    @NotBlank(message = "Name is mandatory.")
    @NotNull(message = "Name cannot be null.")
    private String name;
    private String description;
    private String image;
    private LocalDateTime creationDate;
    private LocalDateTime updatedAt;

    
}
