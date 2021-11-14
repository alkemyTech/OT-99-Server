package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class NewsDto {
	
    private Long id;
    private String name;
    private String content;
    private String image;
    private CategoryDto category;
    private LocalDate creationDate;
    private LocalDate updatedDate;
 
}
