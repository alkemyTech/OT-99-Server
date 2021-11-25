package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.NewsDtoPersist;

import java.time.LocalDate;

import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.dto.NewsPaginable;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.NewsMapper;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.model.News;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.CategoryService;
import com.alkemy.ong.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

		News news = newsRepository.findById(id).orElseThrow(() -> new NotFoundException("The news is not registered."));

		return newsMapper.toNewsDto(news);
	}
	
    @Override
    public void deleteNew(Long id) throws NotFoundException {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("The new of id:" + id + " was not found"));
        news.setDeletedDate(LocalDate.now());
        news.setDeleted(true);
        newsRepository.save(news);
    }

	@Override
	public NewsPaginable getAllByPage(int page) throws NotFoundException {
		Page<News> page1 = newsRepository.findAll(PageRequest.of(page, 10));
		if(page1.getContent().isEmpty()){
			throw new NotFoundException("Don't exist the page");
		}
		return this.createPaginable(page1);
	}

	private NewsPaginable createPaginable(Page<News> pages){
		NewsPaginable newsPaginable = new NewsPaginable();
		newsPaginable.setNews(pages.getContent());
		if(pages.hasNext()){
			newsPaginable.setNextPage("/news?page="+pages.nextPageable().getPageNumber());
		}
		if(pages.hasPrevious()){
			newsPaginable.setPreviousPage("/news?page=" + pages.previousPageable().getPageNumber());
		}
		return newsPaginable;
	}
}
