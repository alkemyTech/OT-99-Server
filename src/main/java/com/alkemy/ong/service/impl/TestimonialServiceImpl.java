package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.TestimonialRequest;
import com.alkemy.ong.exception.DataAlreadyExistException;
import com.alkemy.ong.mapper.TestimonialMapper;
import com.alkemy.ong.model.Testimonial;
import com.alkemy.ong.repository.TestimonialRepository;
import com.alkemy.ong.service.TestimonialService;
import java.time.Instant;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestimonialServiceImpl implements TestimonialService {

    @Autowired
    TestimonialMapper testimonialMapper;
    @Autowired
    TestimonialRepository testimonialRepository;

    @Override
    public Testimonial save(TestimonialRequest testimonialRequest) throws DataAlreadyExistException {

        if ((testimonialRepository.findByName(testimonialRequest.getName()).isPresent())) {
            throw new DataAlreadyExistException("This testimonial's name has already been used");
        }
        testimonialRequest.setCreationDate(Date.from(Instant.now()));
        testimonialRequest.setLastUpdated(Date.from(Instant.now()));
        Testimonial testimonial = testimonialMapper.dtoToEntity(testimonialRequest);
        return testimonialRepository.save(testimonial);

    }

}
