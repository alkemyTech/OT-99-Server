package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.TestimonialRequest;
import com.alkemy.ong.exception.DataAlreadyExistException;
import com.alkemy.ong.exception.NotFoundException;
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

        testimonialRequest.setLastUpdated(Date.from(Instant.now()));
        Testimonial testimonial = testimonialMapper.dtoToEntity(testimonialRequest);
        testimonial.setCreationDate(Date.from(Instant.now()));
        return testimonialRepository.save(testimonial);

    }

    @Override
    public Testimonial updateTestimonial(Long id, TestimonialRequest testimonialRequest) throws NotFoundException, DataAlreadyExistException {

        if (!testimonialRepository.findById(id).isPresent()) {

            throw new NotFoundException("Testimonial could not be found");

        }
        if ((testimonialRepository.findByName(testimonialRequest.getName()).isPresent())) {
            throw new DataAlreadyExistException("This testimonial's name has already been used");
        }

        Testimonial testimonial = testimonialRepository.findById(id).get();
        testimonialMapper.updateEntity(testimonialRequest, testimonial);
        testimonial.setLastUpdated(Date.from(Instant.now()));
        return testimonialRepository.save(testimonial);

    }

    @Override
    public void delete(Long id) throws NotFoundException {
        testimonialRepository.findById(id).orElseThrow(() -> new NotFoundException("Testimonial could not be found"));
        testimonialRepository.deleteById(id);
    }

}
