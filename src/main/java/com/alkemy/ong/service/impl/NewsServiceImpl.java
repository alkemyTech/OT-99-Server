package com.alkemy.ong.service.impl;

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
    private NewsRepository repo;
    @Override
    public NewsDto getById(Long id) throws NotFoundException {
        News news = repo.findById(id).orElseThrow(() -> new NotFoundException("The news is not registered."));
        return NewsDto.mapToDto(news);
    }
}
