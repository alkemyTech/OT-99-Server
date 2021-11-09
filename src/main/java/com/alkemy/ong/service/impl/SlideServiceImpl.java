package com.alkemy.ong.service.impl;

import java.util.List;

import com.alkemy.ong.model.Slide;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.SlideService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlideServiceImpl implements SlideService {

    @Autowired
    SlideRepository slideRepository;

    @Override
    public List<Slide> getAllSlides() {

        return slideRepository.findAll();
    }

}
