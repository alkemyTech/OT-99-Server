package com.alkemy.ong.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.mapper.CommentMapper;
import com.alkemy.ong.model.Comment;
import com.alkemy.ong.repository.CommentRepository;
import com.alkemy.ong.service.CommentService;


@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	CommentMapper commentMapper;
	
	@Override
	public List<CommentDto> getAll() {
		
		List<Comment> comments=commentRepository.findAll(Sort.by(Direction.DESC,"creationDate"));
		
		return commentMapper.toCommentDtoList(comments) ;
	}

}