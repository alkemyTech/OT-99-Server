package com.alkemy.ong.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.alkemy.ong.dto.CategoryDtoGetAll;
import com.alkemy.ong.model.Category;

@Component
public class CategoryMapper {

	public List<CategoryDtoGetAll> toCategoryDtoGetAllList(List<Category> categories){
		
		List<CategoryDtoGetAll> categoriesDto=new ArrayList<>();
		
		if(categories!=null) {

			categoriesDto = categories.stream().map(category -> { return new CategoryDtoGetAll(category.getName()); }).collect(Collectors.toList());
		}
		
		return categoriesDto;
	}
	
}
