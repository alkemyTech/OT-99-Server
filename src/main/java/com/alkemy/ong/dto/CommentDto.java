package com.alkemy.ong.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Data
@Getter
@Setter
@NoArgsConstructor
public class CommentDto {

	@NotEmpty
	private String content;
	
	public CommentDto(String content) {
		this.content=content;
	}
	
}
