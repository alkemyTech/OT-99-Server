package com.alkemy.ong.service;

import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.News;

import java.util.List;

public interface NewsService {
    public NewsDto getById(Long id) throws NotFoundException;
}
