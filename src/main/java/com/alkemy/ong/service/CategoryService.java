package com.alkemy.ong.service;

import javax.validation.Valid;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.CategoryDtoGetAll;
import com.alkemy.ong.dto.PageDto;
import com.alkemy.ong.exception.DataAlreadyExistException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.Category;

public interface CategoryService {

   PageDto<CategoryDtoGetAll> getAllCategories(int page, int size) throws NotFoundException;

   Category save(@Valid CategoryDto categoryDto) throws DataAlreadyExistException;

   Category update(Long id, CategoryDto categoryDto) throws NotFoundException;

   Category getCategoryById(long id) throws NotFoundException;

   void deleteById(Long id) throws NotFoundException;
}
