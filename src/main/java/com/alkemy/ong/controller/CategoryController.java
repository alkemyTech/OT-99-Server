package com.alkemy.ong.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.CategoryDtoGetAll;
import com.alkemy.ong.exception.DataAlreadyExistException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<List<CategoryDtoGetAll>> getAllCategories(){
		
		return new ResponseEntity<>( categoryService.getAllCategories() , HttpStatus.OK );
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category>getById(@Valid @PathVariable long id)throws NotFoundException{
		return new ResponseEntity<>(categoryService.getCategoryById(id),HttpStatus.OK); 
	}

	@PostMapping("/create")
    public ResponseEntity<Category> create(@Valid @RequestBody CategoryDto categoryDto) throws DataAlreadyExistException {
        Category category = categoryService.save(categoryDto);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

}

