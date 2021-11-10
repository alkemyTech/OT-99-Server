package com.alkemy.ong.dto;

import com.alkemy.ong.model.Category;
import com.alkemy.ong.model.News;
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
    private Category category;
    private LocalDate creationDate;
    private LocalDate updatedDate;
    public static NewsDto mapToDto(News news){
        NewsDto newsDto = new NewsDto();
        newsDto.setId(news.getId());
        newsDto.setName(news.getName());
        newsDto.setContent(news.getContent());
        newsDto.setImage(news.getImage());
        newsDto.setCategory(news.getCategory());
        newsDto.setCreationDate(news.getCreationDate());
        newsDto.setUpdatedDate(news.getUpdatedDate());
        return newsDto;
    }
}
