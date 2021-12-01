package com.alkemy.ong.controller;

import java.util.List;

import com.alkemy.ong.dto.CommentDtoResponse;
import com.alkemy.ong.dto.CommentDtoSave;
import com.alkemy.ong.exception.InvalidCredentialsException;
import com.alkemy.ong.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
	public ResponseEntity<CommentDtoResponse> saveComment(@Valid @RequestBody CommentDtoSave commentDtoSave)
			throws NotFoundException {
		return new ResponseEntity<>(commentService.save(commentDtoSave), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteComment(@PathVariable Long id) throws NotFoundException, InvalidCredentialsException {
		commentService.deleteComment(id);
		return new ResponseEntity<>("The comment has been deleted", HttpStatus.OK);
	}

	@GetMapping("/get")
	public String currentUserName(Authentication authentication) {
        return authentication.getName();
    }
}
