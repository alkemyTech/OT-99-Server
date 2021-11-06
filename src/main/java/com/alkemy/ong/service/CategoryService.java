package com.alkemy.ong.service;

import java.util.List;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.CategoryDtoGetAll;
import com.alkemy.ong.exception.DataAlreadyExistException;
import com.alkemy.ong.model.Category;

public interface CategoryService {

   void create(CategoryDto categoryDto) throws DataAlreadyExistException;



}
