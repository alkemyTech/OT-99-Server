package com.alkemy.ong.service;

import com.alkemy.ong.dto.NewsDtoPersist;
import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.News;

import java.util.List;

public interface NewsService {

	public NewsDto save(NewsDtoPersist newsDto) throws NotFoundException;
	
	public NewsDto update(NewsDtoPersist newsDto, Long id) throws NotFoundException;
	
    public NewsDto getById(Long id) throws NotFoundException;

    void deleteNew(Long id) throws NotFoundException;

    public List<News> getAllByPage(int page);
}
