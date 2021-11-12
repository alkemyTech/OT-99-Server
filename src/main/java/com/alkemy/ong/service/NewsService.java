package com.alkemy.ong.service;

import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.exception.NotFoundException;

public interface NewsService {

    NewsDto getById(Long id) throws NotFoundException;

    void deleteNew(Long id) throws NotFoundException;
}
