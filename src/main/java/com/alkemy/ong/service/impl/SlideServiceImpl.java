package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.SlideDtoGet;
import com.alkemy.ong.mapper.SlideMapper;
import java.util.List;
import java.util.stream.Collectors;
import com.alkemy.ong.dto.SlideDto;

import com.alkemy.ong.dto.SlideDtoPost;
import com.alkemy.ong.model.BASE64DecodedMultipartFile;
import com.alkemy.ong.model.Organization;

import com.alkemy.ong.model.Slide;
import javax.persistence.EntityNotFoundException;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.ImageService;
import com.alkemy.ong.service.SlideService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Random;
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
        BASE64DecodedMultipartFile file = this.convert(slide.getImageUrl());
        String path = "src/main/resources/images/";
        String name = UUID.randomUUID().toString();
        file.setName(path+name);
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
    public BASE64DecodedMultipartFile convert(String image) {
        byte[] result = Base64.getDecoder().decode(image);
        BASE64DecodedMultipartFile file = new BASE64DecodedMultipartFile(result);

//        String filepath = "";
//        File file = new File(filepath);
//        this.writeFile(file, result);
        return file;

    }

//    public void writeFile(File file, byte[] result) {
//        try {
//            FileOutputStream os = new FileOutputStream(file);
//            os.write(result);
//            os.close();
//
//        } catch (Exception e) {
//            System.out.println("Exception: " + e);
//        }
//    }


	@Override
	public List<SlideDtoGet> getAllSlidesByOrganization(Organization org) {
		
		List<Slide> slides=slideRepository.findAllByOrganizationId(org);
		
		return slideMapper.toSlideDtoGetList(slides);
	}

}
