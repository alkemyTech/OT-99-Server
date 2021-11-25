package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.dto.TestimonialRequest;
import com.alkemy.ong.exception.DataAlreadyExistException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.TestimonialMapper;
import com.alkemy.ong.model.Testimonial;
import com.alkemy.ong.repository.TestimonialRepository;
import com.alkemy.ong.service.TestimonialService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

		Testimonial testimonial = testimonialMapper.dtoToEntity(testimonialRequest);

		return testimonialRepository.save(testimonial);

	}

	@Override
	public Testimonial updateTestimonial(Long id, TestimonialRequest testimonialRequest)
			throws NotFoundException, DataAlreadyExistException {

		Optional<Testimonial> optTestimonial = testimonialRepository.findById(id);
		
		if (optTestimonial.isEmpty()) {

			throw new NotFoundException("Testimonial could not be found");
		}
		
		Testimonial testimonial=optTestimonial.get();
		
	
		if ((testimonialRepository.findByName(testimonialRequest.getName()).isPresent())) {
			
				testimonialMapper.updateEntityWithoutName(testimonialRequest, testimonial);	
				
		}else {
			
			testimonialMapper.updateEntity(testimonialRequest, testimonial);
		}

		return testimonialRepository.save(testimonial);

	}

	@Override
	public void delete(Long id) throws NotFoundException {

		if (!testimonialRepository.existsById(id)) {

			throw new NotFoundException("Testimonial could not be found");
		}

		testimonialRepository.deleteById(id);
	}

	@Override
	public List<TestimonialDto> getPage(Integer page){

		Pageable pageable = PageRequest.of(page, 10);
		
		Page<Testimonial> pageRecovered = testimonialRepository.findAll(pageable);

		return testimonialMapper.toTestimonialDtoList(pageRecovered);
	}

}
