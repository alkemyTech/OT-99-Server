package com.alkemy.ong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.service.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	CommentService commentService;

	@GetMapping
	public ResponseEntity<List<CommentDto>> getAll() {

		return new ResponseEntity<>(commentService.getAll(), HttpStatus.OK);
	}
}
