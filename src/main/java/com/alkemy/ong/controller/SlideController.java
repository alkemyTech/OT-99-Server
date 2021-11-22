package com.alkemy.ong.controller;

import java.util.List;

import javax.validation.Valid;

import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.service.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.alkemy.ong.dto.SlideDtoGet;
import com.alkemy.ong.dto.SlideDtoUpdate;
import com.alkemy.ong.exception.NotFoundException;

@RestController
@RequestMapping("/slides")
public class SlideController {

    @Autowired
    SlideService slideService;

    @GetMapping
    public ResponseEntity<List<SlideDto>> getAllSlides() {

        return new ResponseEntity<>(slideService.getAllSlides(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<SlideDtoGet> getSlide(@PathVariable Long id) {

        return new ResponseEntity<>(slideService.getSlide(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        slideService.deleteSlide(id);

        return new ResponseEntity<>("Slide has been deleted", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SlideDtoUpdate> updateSlide(@PathVariable Long id,
            @Valid @RequestBody SlideDtoUpdate slideDto) throws NotFoundException {
        return new ResponseEntity<>(slideService.updateSlide(id, slideDto), HttpStatus.OK);
    }
}
