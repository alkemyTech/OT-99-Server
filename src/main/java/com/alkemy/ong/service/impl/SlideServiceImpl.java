package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.SlideDtoGet;
import com.alkemy.ong.mapper.SlideMapper;
import java.util.List;
import java.util.stream.Collectors;
import com.alkemy.ong.dto.SlideDto;

import com.alkemy.ong.dto.SlideDtoPost;
import com.alkemy.ong.model.Base64DecodedMultipartFile;
import com.alkemy.ong.model.Organization;

import com.alkemy.ong.model.Slide;
import javax.persistence.EntityNotFoundException;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.ImageService;
import com.alkemy.ong.service.SlideService;

import java.util.Base64;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlideServiceImpl implements SlideService {

    @Autowired
    SlideRepository slideRepository;

    @Autowired
    SlideMapper slideMapper;

    @Autowired
    ImageService imageService;

    @Override
    public Slide create(SlideDtoPost slideDtoPost) {
        Slide slide = slideMapper.dtoToEntity(slideDtoPost);
        Base64DecodedMultipartFile file = this.convert(slide.getImageUrl());
        String name = UUID.randomUUID().toString();
        String extension = ".jpg";
        file.setName(name+extension);
        String image = imageService.uploadFile(file);
        slide.setImageUrl(image);
        return slideRepository.save(slide);

    }


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
    public Base64DecodedMultipartFile convert(String image) {
        byte[] result = Base64.getDecoder().decode(image);
        Base64DecodedMultipartFile file = new Base64DecodedMultipartFile(result);
        return file;

    }


	@Override
	public List<SlideDtoGet> getAllSlidesByOrganization(Organization org) {
		
		List<Slide> slides=slideRepository.findAllByOrganizationId(org);
		
		return slideMapper.toSlideDtoGetList(slides);
	}

}
