package com.alkemy.ong.dto;

import com.alkemy.ong.model.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CategoryDto {
    private String name;
    private String description;
    private String image;

    public static Category mapToEntity(CategoryDto cat){
        Category c = new Category();
        c.setName(cat.name);
        c.setDescription(cat.description);
        c.setImage(cat.image);

        return c;
    }


    
}
