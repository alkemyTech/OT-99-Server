package com.alkemy.ong.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class NewsDtoPersist {

	@NotBlank(message = "Name is mandatory.")
	private String name;
	
	@NotBlank(message = "Content is mandatory.")
	private String content;
	
	@NotBlank(message = "Image is mandatory.")
	private String image;
	
	@NotNull(message="CategoryId is mandatory.")
	@Range(min=1, message="CategoryId can not be negative or 0")
	private Long categoryId;
}
