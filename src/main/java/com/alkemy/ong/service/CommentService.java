package com.alkemy.ong.service;

import java.util.List;

import com.alkemy.ong.dto.CommentDto;

public interface CommentService {
    
	List<CommentDto> getAll();
}