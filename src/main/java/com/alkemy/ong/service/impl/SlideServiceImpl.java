package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.SlideDtoGet;
import com.alkemy.ong.dto.SlideDtoUpdate;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.SlideMapper;
import java.util.List;
import java.util.stream.Collectors;
import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.model.Slide;
import javax.persistence.EntityNotFoundException;

import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.SlideService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlideServiceImpl implements SlideService {

    @Autowired
    SlideRepository slideRepository;

    @Autowired
    SlideMapper slideMapper;

    @Autowired
    OrganizationRepository organizationRepository;

    @Override
    public List<SlideDto> getAllSlides() {

        List<Slide> slides = slideRepository.findAll();

        return slides.stream().map(slide -> new SlideDto(slide.getImageUrl(), slide.getSlideOrder()))
                .collect(Collectors.toList());
    }

    @Override
    public SlideDtoGet getSlide(Long id) {

        Slide slide = slideRepository.getById(id);

        return slideMapper.toSlideDtoGet(slide);

    }

    @Override
    public void deleteSlide(Long id) throws EntityNotFoundException {
        if (slideRepository.existsById(id)) {
            slideRepository.deleteById(id);

        } else {
            throw new EntityNotFoundException("Slide does not exist");
        }

    }

    @Override
    public SlideDtoUpdate updateSlide(Long id, SlideDtoUpdate slideDto) throws NotFoundException {
        if (!slideRepository.existsById(id)) {
            throw new NotFoundException("Couldn't find slide with id : " + id);
        }
        if (organizationRepository.existsById(slideDto.getOrganizationId())) {
            Slide slide = slideMapper.toSlide(slideDto);
            slide.setId(id);
            slide.setOrganizationId(organizationRepository.getById(slideDto.getOrganizationId()));
            slideRepository.save(slide);
            slideDto.setId(id);
            return slideDto;
        } else {
            throw new NotFoundException("Couldn't find organization with id: " + slideDto.getOrganizationId());
        }
    }
}
