package com.alkemy.ong.service.impl;

import java.time.LocalDate;

import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.News;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements NewsService {
    
    @Autowired
    private NewsRepository newsRepository;

    @Override
    public NewsDto getById(Long id) throws NotFoundException {
        News news = newsRepository.findById(id).orElseThrow(() -> new NotFoundException("The news is not registered."));
        return NewsDto.mapToDto(news);
    }

    @Override
    public void deleteNew(Long id) throws NotFoundException {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("The new of id:" + id + " was not found"));
        news.setDeletedDate(LocalDate.now());
        news.setDeleted(true);
        newsRepository.save(news);
    }
}
