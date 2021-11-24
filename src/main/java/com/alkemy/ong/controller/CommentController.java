package com.alkemy.ong.controller;

import java.util.List;

import com.alkemy.ong.dto.CommentDtoResponse;
import com.alkemy.ong.dto.CommentDtoSave;
import com.alkemy.ong.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.service.CommentService;

import javax.validation.Valid;

@RestController
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	CommentService commentService;

	@GetMapping
	public ResponseEntity<List<CommentDto>> getAll() {

		return new ResponseEntity<>(commentService.getAll(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<CommentDtoResponse> saveComment(@Valid @RequestBody CommentDtoSave commentDtoSave) throws NotFoundException {
		return new ResponseEntity<>(commentService.save(commentDtoSave),HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CommentDto> updateComment(@Valid @RequestBody CommentDto commentDto,@PathVariable Long id) throws NotFoundException {
		return new ResponseEntity<>(commentService.update(commentDto,id),HttpStatus.OK);
	}

}
