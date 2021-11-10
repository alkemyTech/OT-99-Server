package com.alkemy.ong.mapper;

import java.util.*;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.CategoryDtoGetAll;
import com.alkemy.ong.model.Category;

@Component
public class CategoryMapper {

    @Autowired
    ModelMapper modelMapper;

	public List<CategoryDtoGetAll> toCategoryDtoGetAllList(List<Category> categories){

		List<CategoryDtoGetAll> categoriesDto=new ArrayList<>();

		if(categories!=null) {

			categoriesDto = categories.stream().map(category -> { return new CategoryDtoGetAll(category.getName()); }).collect(Collectors.toList());
        }

		return categoriesDto;
	}

   public Category dtoToEntity(CategoryDto catDto){
   Category category = modelMapper.map(catDto, Category.class);
     return category;
   }
}
