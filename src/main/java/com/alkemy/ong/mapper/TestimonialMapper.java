package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.TestimonialRequest;
import com.alkemy.ong.model.Testimonial;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestimonialMapper {

    @Autowired
    ModelMapper modelMapper;

    public Testimonial dtoToEntity(TestimonialRequest testimonialRequest) {
        Testimonial testimonial = modelMapper.map(testimonialRequest, Testimonial.class);
        return testimonial;
    }
}
