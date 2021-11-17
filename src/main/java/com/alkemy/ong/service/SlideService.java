package com.alkemy.ong.service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import com.alkemy.ong.dto.SlideDtoGet;
import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.model.Slide;
import java.io.File;
import java.io.IOException;

public interface SlideService {

    public void deleteSlide(Long id) throws EntityNotFoundException;

    List<SlideDto> getAllSlides();

    SlideDtoGet getSlide(Long id);
    
    Slide create(SlideDtoGet slideDtoGet);

    File convert(String image);

}
