package com.alkemy.ong.mapper;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alkemy.ong.dto.NewsDtoPersist;
import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.model.News;

@Component
public class NewsMapper {
	
	@Autowired
	CategoryMapper categoryMapper;
	
	public News toNews(NewsDtoPersist newsDto, Category category) {

		News news = new News();

		setPrincipalAttributes(newsDto, category, news);
		news.setCreationDate(LocalDate.now());

		return news;

	}

	public NewsDto toNewsDto(News news) {

		NewsDto newsDto = new NewsDto();

		if (news != null) {

			newsDto.setId(news.getId());
			newsDto.setName(news.getName());
			newsDto.setContent(news.getContent());
			newsDto.setImage(news.getImage());
			newsDto.setCategory(categoryMapper.toCategoryDto(news.getCategory()));
			newsDto.setCreationDate(news.getCreationDate());
			newsDto.setUpdatedDate(news.getUpdatedDate());
		}

		return newsDto;

	}

	public void updateNews(News news, NewsDtoPersist newsDto, Category category) {

		setPrincipalAttributes(newsDto, category, news);

	}

	private void setPrincipalAttributes(NewsDtoPersist newsDto, Category category, News news) {
		news.setName(newsDto.getName());
		news.setContent(newsDto.getContent());
		news.setImage(newsDto.getImage());
		news.setCategory(category);
		news.setUpdatedDate(LocalDate.now());
	}
}
