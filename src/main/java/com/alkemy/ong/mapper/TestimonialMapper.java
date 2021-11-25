package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.dto.TestimonialRequest;
import com.alkemy.ong.model.Testimonial;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class TestimonialMapper {
    
    @Autowired
    ModelMapper modelMapper;
    
    public Testimonial dtoToEntity(TestimonialRequest testimonialRequest) {
    	
        Testimonial testimonial = modelMapper.map(testimonialRequest, Testimonial.class);
        
        return testimonial;
    }
    
    public void updateEntity(TestimonialRequest testimonialRequest, Testimonial testimonial) {
    	
        modelMapper.map(testimonialRequest, testimonial);
        
    }
    
    public List<TestimonialDto> toTestimonialDtoList(Page<Testimonial> testimonialPage){
    	
    	List<TestimonialDto> testimonialDtos=new ArrayList<>();
    	
    	if(testimonialPage.hasContent()) {
    			
    		testimonialDtos=testimonialPage.getContent().stream().map(testimonial -> { 
    			
    			return new TestimonialDto(testimonial.getId(),testimonial.getName(),testimonial.getImage(),testimonial.getContent());
    			
    			}).collect(Collectors.toList());
    	}
    	
		return testimonialDtos;
    	
    }
}
