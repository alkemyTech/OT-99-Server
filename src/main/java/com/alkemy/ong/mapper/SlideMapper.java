package com.alkemy.ong.mapper;

import org.springframework.stereotype.Component;

import com.alkemy.ong.dto.SlideDtoGet;
import com.alkemy.ong.model.Slide;

@Component
public class SlideMapper {

	public SlideDtoGet toSlideDtoGet(Slide slide) {
		
		SlideDtoGet slideDto=new SlideDtoGet();
		slideDto.setImageUrl(slide.getImageUrl());
		slideDto.setText(slide.getText());
		slideDto.setSlideOrder(slide.getSlideOrder());
		
		return slideDto;
	}
}