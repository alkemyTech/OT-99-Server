package com.alkemy.ong.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.model.Comment;

public class CommentMapper {

	public List<CommentDto> toCommentDtoList(List<Comment> comments) {
		
		List<CommentDto> dtos=new ArrayList<>();
		
		if(comments!=null) {
			
			dtos=comments.stream().map(comment-> { return new CommentDto(comment.getContent()); }).collect(Collectors.toList());
		}
		
		return dtos;
	}

}
