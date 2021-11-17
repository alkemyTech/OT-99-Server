package com.alkemy.ong.mapper;

import org.springframework.stereotype.Component;

import com.alkemy.ong.dto.SlideDtoGet;
import com.alkemy.ong.dto.SlideDtoPost;
import com.alkemy.ong.model.Slide;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class SlideMapper {

    @Autowired
    ModelMapper modelMapper;

    public Slide dtoToEntity(SlideDtoPost slideDto) {
        Slide slide = modelMapper.map(slideDto, Slide.class);
        return slide;
    }

    public SlideDtoGet toSlideDtoGet(Slide slide) {

        SlideDtoGet slideDto = new SlideDtoGet();
        slideDto.setImageUrl(slide.getImageUrl());
        slideDto.setText(slide.getText());
        slideDto.setSlideOrder(slide.getSlideOrder());

        return slideDto;
    }
}
