package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.model.Slide;

public class SlideMapper {

	public SlideDto toSlideDto(Slide slide) {
		
		SlideDto slideDto=new SlideDto();
		slideDto.setId(slide.getId());
		slideDto.setImageUrl(slide.getImageUrl());
		slideDto.setText(slide.getText());
		slideDto.setSlideOrder(slide.getSlideOrder());
		
		return slideDto;
	}
}
