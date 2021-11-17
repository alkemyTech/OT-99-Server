package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.SlideDtoGet;
import com.alkemy.ong.mapper.SlideMapper;
import java.util.List;
import java.util.stream.Collectors;
import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.model.Slide;
import javax.persistence.EntityNotFoundException;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.ImageService;
import com.alkemy.ong.service.SlideService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import javax.tools.FileObject;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SlideServiceImpl implements SlideService {

    @Autowired
    SlideRepository slideRepository;

    @Autowired
    SlideMapper slideMapper;
    
    @Autowired 
    ImageService imageService;

    @Override
    public Slide create(SlideDtoGet slideDtoGet) {
       Slide slide = new Slide();
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

    public File convert(String image) {
        byte[] result = Base64.getDecoder().decode(image);
        String filepath = "";
        File file = new File(filepath);
        this.writeFile(file, result);
        return file;

    }

    public void writeFile(File file, byte[] result) {
        try {
            OutputStream os = new FileOutputStream(file);
            os.write(result);
            os.close();

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

}
