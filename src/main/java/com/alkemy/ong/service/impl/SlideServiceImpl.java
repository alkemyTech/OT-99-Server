package com.alkemy.ong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alkemy.ong.dto.SlideDtoGet;
import com.alkemy.ong.mapper.SlideMapper;
import com.alkemy.ong.model.Slide;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.SlideService;

@Service
public class SlideServiceImpl implements SlideService{

	@Autowired
	SlideRepository slideRepository;
	
	@Autowired
	SlideMapper slideMapper;

	@Override
	public SlideDtoGet getSlide(Long id) {
		
		Slide slide=slideRepository.getById(id);
	
		return slideMapper.toSlideDtoGet(slide);
		
	}

}
