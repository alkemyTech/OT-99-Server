package com.alkemy.ong.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.alkemy.ong.model.Slide;
import com.alkemy.ong.service.SlideService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@RestController
@RequestMapping("/slides")
public class SlideController {

    @Autowired
    SlideService slideService;

    @GetMapping
    public ResponseEntity<List<SlideDto>> getAllCategories() {
        List<Slide> slides = slideService.getAllSlides();
        List<SlideDto> slideDtos = slides.stream().map(
            slide -> new SlideDto(slide.getImageUrl(), slide.getSlideOrder())).collect(Collectors.toList());
        return new ResponseEntity<>(slideDtos, HttpStatus.OK);
    }

    @AllArgsConstructor
    @Getter
    @Setter
    private class SlideDto {
        private String imageUrl;
        private Integer slideOrder;
    }

}
