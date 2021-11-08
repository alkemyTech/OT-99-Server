package com.alkemy.ong.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.CategoryDtoGetAll;
import com.alkemy.ong.exception.DataAlreadyExistException;
import com.alkemy.ong.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/categories")
public class CategoryController {
   
    @Autowired
    private CategoryService categoryService;

    @GetMapping
	public ResponseEntity<List<CategoryDtoGetAll>> getAllCategories(){

		return new ResponseEntity<>( categoryService.getAllCategories() , HttpStatus.OK );
	}

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody CategoryDto categoryDto) throws DataAlreadyExistException {
        categoryService.save(categoryDto);
        return new ResponseEntity<>(categoryDto, HttpStatus.CREATED);
    }



}

