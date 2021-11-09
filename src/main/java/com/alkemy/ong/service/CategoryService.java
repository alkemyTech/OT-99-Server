package com.alkemy.ong.service;

import java.util.List;

import javax.validation.Valid;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.CategoryDtoGetAll;
import com.alkemy.ong.exception.DataAlreadyExistException;
import com.alkemy.ong.model.Category;

public interface CategoryService {

   List<CategoryDtoGetAll> getAllCategories();
   Category save(@Valid CategoryDto categoryDto) throws DataAlreadyExistException;

}
