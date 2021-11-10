package com.alkemy.ong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.mapper.SlideMapper;
import com.alkemy.ong.model.Slide;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.SlideService;

public class SlideServiceImpl implements SlideService{

	@Autowired
	SlideRepository slideRepository;
	
	@Autowired
	SlideMapper slideMapper;

	@Override
	public SlideDto getSlide(Long id) {
		
		Slide slide=slideRepository.getById(id);
	
		return slideMapper.toSlideDto(slide);
		
	}

}
