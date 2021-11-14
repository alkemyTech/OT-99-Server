package com.alkemy.ong.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.CategoryDtoGetAll;
import com.alkemy.ong.exception.DataAlreadyExistException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.Category;

public interface CategoryService {

   List<CategoryDtoGetAll> getAllCategories();
   Category save(@Valid CategoryDto categoryDto) throws DataAlreadyExistException;
   Category update(Long id,CategoryDto categoryDto) throws NotFoundException;
   Category getCategoryById(long id) throws NotFoundException;
   void deleteById(Long id) throws NotFoundException;
}
