package com.alkemy.ong.service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import com.alkemy.ong.dto.SlideDtoGet;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.dto.SlideDto;

public interface SlideService {

    public void deleteSlide(Long id) throws EntityNotFoundException;

    List<SlideDto> getAllSlides();
    
    List<SlideDtoGet> getAllSlides(Organization org);
    
	SlideDtoGet getSlide(Long id);

}

