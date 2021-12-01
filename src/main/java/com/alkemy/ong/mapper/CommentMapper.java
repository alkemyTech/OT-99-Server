package com.alkemy.ong.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.model.Comment;

@Component
public class CommentMapper {
	
	
	public List<CommentDto> toCommentDtoList(List<Comment> comments) {
		
		List<CommentDto> dtos=new ArrayList<>();
		if(comments!=null) dtos=comments.stream().map(comment-> { return new CommentDto(comment.getContent()); }).collect(Collectors.toList());
	
		return dtos;
	}
	
	public static CommentDto mapToDto(Comment comment){
		CommentDto commentDto = new CommentDto(comment.getContent());
		return commentDto;
	}
}
