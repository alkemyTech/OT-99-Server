package com.alkemy.ong.service;

import com.alkemy.ong.dto.SlideDtoGet;
import com.alkemy.ong.dto.SlideDto;
import java.util.List;

public interface SlideService {

	SlideDtoGet getSlide(Long id);

  List<SlideDto> getAllSlides();


