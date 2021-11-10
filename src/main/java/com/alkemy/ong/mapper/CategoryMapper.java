package com.alkemy.ong.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.alkemy.ong.dto.CategoryDtoGetAll;
import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.dto.CategoryDetailsDto;


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

	public Category toCategoryDetailsDto(long id) {
			Category category = modelMapper.map(id, Category.class);
			return category;
		}

	public Category dtoToEntity(long id) {
			Category category = modelMapper.map(id, Category.class);
			return category;
		  }

}
