package com.alkemy.ong.service;

import javax.persistence.EntityNotFoundException;

import java.util.List;

import com.alkemy.ong.dto.SlideDto;

public interface SlideService {

    public void deleteSlide(Long id) throws EntityNotFoundException;

    List<SlideDto> getAllSlides();

}
