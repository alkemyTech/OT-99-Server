package com.alkemy.ong.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MemberDto {

    @NotBlank(message = "Name is mandatory.")
    public String name;
    public String facebook;
    public String instagram;
    public String linkedin;
    public String image;
    public String description;
    public LocalDate creationDate;
    public LocalDate updatedDate;
}