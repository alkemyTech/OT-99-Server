package com.alkemy.ong.service;

import com.alkemy.ong.dto.TestimonialRequest;
import com.alkemy.ong.exception.DataAlreadyExistException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.Testimonial;
import com.alkemy.ong.dto.PageDto;
import com.alkemy.ong.dto.TestimonialDto;

public interface TestimonialService {

    public Testimonial save(TestimonialRequest testimonialRequest) throws DataAlreadyExistException;
    
    public Testimonial updateTestimonial(Long id, TestimonialRequest testimonialRequest) throws NotFoundException, DataAlreadyExistException;

    public void delete(Long id) throws NotFoundException;
    
    public PageDto<TestimonialDto> getPage (Integer page,Integer sizePage,String sortBy)throws NotFoundException;
}
