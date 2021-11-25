package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.dto.TestimonialRequest;
import com.alkemy.ong.model.Testimonial;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
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
       	testimonial.setLastUpdated(Date.from(Instant.now()));
		testimonial.setCreationDate(Date.from(Instant.now()));
        
        return testimonial;
    }
    
    public void updateEntity(TestimonialRequest testimonialRequest, Testimonial testimonial) {
    	
    	testimonial.setName(testimonialRequest.getName());
    	updateEntityWithoutName(testimonialRequest, testimonial);
        
    }

	
    public void updateEntityWithoutName(TestimonialRequest testimonialRequest, Testimonial testimonial) {
		
    	testimonial.setImage(testimonialRequest.getImage());
        testimonial.setContent(testimonialRequest.getContent());
        testimonial.setLastUpdated(Date.from(Instant.now()));
		
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
