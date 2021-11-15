package com.alkemy.ong.service;

import com.alkemy.ong.dto.TestimonialRequest;
import com.alkemy.ong.exception.DataAlreadyExistException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.Testimonial;

public interface TestimonialService {

    public Testimonial save(TestimonialRequest testimonialRequest) throws DataAlreadyExistException;
    public Testimonial updateTestimonial(Long id, TestimonialRequest testimonialRequest) throws NotFoundException, DataAlreadyExistException;
}
