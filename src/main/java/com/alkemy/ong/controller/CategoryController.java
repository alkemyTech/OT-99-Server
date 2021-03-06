package com.alkemy.ong.controller;

import javax.validation.Valid;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.CategoryDtoGetAll;
import com.alkemy.ong.dto.PageDto;
import com.alkemy.ong.exception.DataAlreadyExistException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping(params = { "page", "size" })
	public ResponseEntity<PageDto<CategoryDtoGetAll>> getAllCategories(@RequestParam int page, @RequestParam int size)
			throws NotFoundException {
		return new ResponseEntity<>(categoryService.getAllCategories(page, size), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> getById(@Valid @PathVariable long id) throws NotFoundException {
		return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<Category> create(@Valid @RequestBody CategoryDto categoryDto)
			throws DataAlreadyExistException {
		Category category = categoryService.save(categoryDto);
		return new ResponseEntity<>(category, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	ResponseEntity<Category> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Long id)
			throws NotFoundException {
		return new ResponseEntity<>(categoryService.update(id, categoryDto), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) throws NotFoundException {
		categoryService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
