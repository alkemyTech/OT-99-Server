package com.alkemy.ong.dto;

import lombok.Data;

@Data
public class CommentDto {

	private String content;
	
	public CommentDto(String content) {
		this.content=content;
	}
	
}
