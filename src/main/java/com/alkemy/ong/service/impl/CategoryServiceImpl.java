package com.alkemy.ong.service.impl;

import java.util.List;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.CategoryDtoGetAll;
import com.alkemy.ong.exception.DataAlreadyExistException;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.CategoryService;
import com.alkemy.ong.mapper.*;
import com.alkemy.ong.validator.DtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    DtoValidator dtoValidator;

    @Override
    public void create(CategoryDto categoryDto) throws DataAlreadyExistException {

        if ((categoryRepository.findByName(categoryDto.getName()).isPresent())) {
            throw new DataAlreadyExistException();
            }
        Category category = CategoryDto.mapToEntity(categoryDto);
        categoryRepository.save(category);
    }



}
