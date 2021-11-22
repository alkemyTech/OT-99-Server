package com.alkemy.ong.service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import com.alkemy.ong.dto.SlideDtoGet;
import com.alkemy.ong.dto.SlideDtoUpdate;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.dto.SlideDto;

public interface SlideService {

    public void deleteSlide(Long id) throws EntityNotFoundException;

    List<SlideDto> getAllSlides();
    
	SlideDtoGet getSlide(Long id);

    SlideDtoUpdate updateSlide(Long id, SlideDtoUpdate slideDto) throws NotFoundException;

}

