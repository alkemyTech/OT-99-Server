package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.NewsDtoPersist;
import java.time.LocalDate;
import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.NewsMapper;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.model.News;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.CategoryService;
import com.alkemy.ong.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements NewsService {


	@Autowired
	private NewsRepository newsRepository;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private NewsMapper newsMapper;
	
	@Override
	public NewsDto save(NewsDtoPersist newsDto) throws NotFoundException {
		
		Category category=categoryService.getCategoryById(newsDto.getCategoryId());
		
		News news=newsRepository.save(newsMapper.toNews(newsDto, category));
		
		return newsMapper.toNewsDto(news);
	}

	@Override
	public NewsDto update(NewsDtoPersist newsDto, Long id) throws NotFoundException {
		
		News news=newsRepository.getById(id);
		
		Category category=categoryService.getCategoryById(newsDto.getCategoryId());
		
		newsMapper.updateNews(news,newsDto, category);
		
		news=newsRepository.save(news);
		
		return newsMapper.toNewsDto(news);
	}

	@Override
	public NewsDto getById(Long id) throws NotFoundException {

		News news = newsRepository.findById(id).orElseThrow(() -> new NotFoundException("This news is not registered."));

		return newsMapper.toNewsDto(news);
	}
	
    @Override
    public void deleteNews(Long id) throws NotFoundException {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("News of id:" + id + " was not found"));
        news.setDeletedDate(LocalDate.now());
        news.setDeleted(true);
        newsRepository.save(news);
    }
    
}
