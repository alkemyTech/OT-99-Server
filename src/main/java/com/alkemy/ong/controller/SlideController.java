package com.alkemy.ong.controller;

import java.util.List;
import com.alkemy.ong.dto.SlideDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alkemy.ong.dto.SlideDtoGet;
import com.alkemy.ong.service.SlideService;

@RestController
@RequestMapping("/slides")
public class SlideController {

	@Autowired
	SlideService slideService;
	
	@GetMapping("/{id}")
	public ResponseEntity<SlideDtoGet> getSlide(@PathVariable Long id){
		
		return new ResponseEntity<>(slideService.getSlide(id),HttpStatus.OK);
	}
  
  @GetMapping
  public ResponseEntity<List<SlideDto>> getAllCategories() {
    
    return new ResponseEntity<>(slideService.getAllSlides(), HttpStatus.OK);
    
    }

}
