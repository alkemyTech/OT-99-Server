package com.alkemy.ong.service.impl;

import java.time.LocalDateTime;
import java.util.*;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.CategoryDtoGetAll;
import com.alkemy.ong.exception.DataAlreadyExistException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.ong.mapper.CategoryMapper;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.CategoryService;



@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryMapper categoryMapper;


    @Override
    public Category save(CategoryDto categoryDto) throws DataAlreadyExistException {

        if ((categoryRepository.findByName(categoryDto.getName()).isPresent())) {
            throw new DataAlreadyExistException();
            }
        Category category = categoryMapper.dtoToEntity(categoryDto);
        categoryDto.setCreationDate(LocalDateTime.now());
        categoryDto.setUpdatedAt(LocalDateTime.now());
        
        return categoryRepository.save(category);
    }

	@Override
	public List<CategoryDtoGetAll> getAllCategories() {

		List<Category> categories=categoryRepository.findAll();

		return categoryMapper.toCategoryDtoGetAllList(categories);
	}
  
}
