package com.alkemy.ong.controller;

import java.util.List;

import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.model.Slide;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/slides")
public class SlideController {

    @Autowired
    SlideService slideService;
    ///DELETE  
    @Autowired 
    SlideRepository sr;

    @GetMapping

    public ResponseEntity<List<SlideDto>> getAllCategories() {
        return new ResponseEntity<>(slideService.getAllSlides(), HttpStatus.OK);
  
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<?> delete(@PathVariable Long id) {
        slideService.deleteSlide(id);
        return new ResponseEntity<>("Slide has been deleted", HttpStatus.OK);
    }
    @PostMapping
    public Slide post(@RequestBody Slide slide){
        return sr.save(slide);
    }
}

