package com.alkemy.ong.controller;

import java.util.List;

import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.service.SlideService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/slides")
public class SlideController {

    @Autowired
    SlideService slideService;

    @GetMapping
    public ResponseEntity<List<SlideDto>> getAllCategories() {
        return new ResponseEntity<>(slideService.getAllSlides(), HttpStatus.OK);
    }

}
