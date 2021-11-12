package com.alkemy.ong.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ContactDto {

	@NotBlank(message = "Email is mandatory.")
    @Email(message = "Email should be valid.", regexp = "[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")
	private String email;
	
	@NotBlank(message = "Name is mandatory.")
	private String name;
	
	private Double phone; 
	
	private String message;
}
