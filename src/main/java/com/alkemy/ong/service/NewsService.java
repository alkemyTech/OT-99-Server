package com.alkemy.ong.service;

import com.alkemy.ong.dto.NewsDtoPersist;
import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.dto.NewsPaginable;
import com.alkemy.ong.exception.NotFoundException;

public interface NewsService {

	public NewsDto save(NewsDtoPersist newsDto) throws NotFoundException;
	
	public NewsDto update(NewsDtoPersist newsDto, Long id) throws NotFoundException;
	
    public NewsDto getById(Long id) throws NotFoundException;

    void deleteNews(Long id) throws NotFoundException;

    public NewsPaginable getAllByPage(int page) throws NotFoundException;
}
