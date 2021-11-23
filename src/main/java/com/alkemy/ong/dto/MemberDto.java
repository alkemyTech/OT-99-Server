package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
public class MemberDto {
    @NotBlank
    private String image;
    private String facebook;
    private String linkedin;
    private String instagram;
    private String description;
    @NotBlank
    private String name;
    private LocalDate creationDate;
    private LocalDate updatedDate;
}
