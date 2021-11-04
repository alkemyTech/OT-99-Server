package com.alkemy.ong.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.alkemy.ong.dto.CategoryDtoGetAll;
import com.alkemy.ong.model.Category;

@Component
public class CategoryMapper {

	public List<CategoryDtoGetAll> toCategoryDtoGetAllList(List<Category> categories){
		
		List<CategoryDtoGetAll> categoriesDto=new ArrayList<>();
		
		if(categories!=null) {
			
			for(Category categ:categories) {
				
				CategoryDtoGetAll categoryDto=new CategoryDtoGetAll();
				
				categoryDto.setName(categ.getName());
				
				categoriesDto.add(categoryDto);
			}
		}
		
		return categoriesDto;
	}
	
}
