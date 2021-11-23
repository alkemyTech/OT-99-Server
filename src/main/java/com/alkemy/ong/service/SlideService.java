package com.alkemy.ong.service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import com.alkemy.ong.dto.SlideDtoGet;
import com.alkemy.ong.dto.SlideDtoUpdate;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.dto.SlideDtoPost;
import com.alkemy.ong.model.Base64DecodedMultipartFile;
import com.alkemy.ong.model.Slide;

public interface SlideService {

    public void deleteSlide(Long id) throws EntityNotFoundException;

    List<SlideDto> getAllSlides();

    Slide create(SlideDtoPost slideDtoPost);

    Base64DecodedMultipartFile convert(String image);

    List<SlideDtoGet> getAllSlidesByOrganization(Organization org);

    SlideDtoGet getSlide(Long id);

    SlideDtoUpdate updateSlide(Long id, SlideDtoUpdate slideDto) throws NotFoundException;

}
