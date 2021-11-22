package com.alkemy.ong.mapper;

import org.springframework.stereotype.Component;

import com.alkemy.ong.dto.SlideDtoGet;
import com.alkemy.ong.dto.SlideDtoUpdate;
import com.alkemy.ong.model.Slide;

@Component
public class SlideMapper {

	public SlideDtoGet toSlideDtoGet(Slide slide) {

		SlideDtoGet slideDto = new SlideDtoGet();
		slideDto.setImageUrl(slide.getImageUrl());
		slideDto.setText(slide.getText());
		slideDto.setSlideOrder(slide.getSlideOrder());

		return slideDto;
	}

	public Slide toSlide(SlideDtoUpdate slideDto) {
		Slide slide = new Slide();
		slide.setImageUrl(slideDto.getImageUrl());
		slide.setText(slideDto.getText());
		slide.setSlideOrder(slideDto.getSlideOrder());
		return slide;
	}
}
