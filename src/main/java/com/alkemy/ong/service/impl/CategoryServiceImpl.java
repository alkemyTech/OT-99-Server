package com.alkemy.ong.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.ong.dto.CategoryDtoGetAll;
import com.alkemy.ong.mapper.CategoryMapper;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	
	CategoryMapper categoryMapper;
	
	@Override
	public List<CategoryDtoGetAll> getAllCategories() {
		
		List<Category> categories=categoryRepository.findAll();
		
		return categoryMapper.toCategoryDtoGetAllList(categories);
	}
	
}
