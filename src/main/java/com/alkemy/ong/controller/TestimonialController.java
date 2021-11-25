package com.alkemy.ong.controller;

import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.dto.TestimonialRequest;
import com.alkemy.ong.exception.DataAlreadyExistException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.Testimonial;
import com.alkemy.ong.service.TestimonialService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/testimonials")
public class TestimonialController {

    @Autowired
    TestimonialService testimonialService;

    @PostMapping
    public ResponseEntity<Testimonial> post(@Valid @RequestBody TestimonialRequest testimonialRequest) throws DataAlreadyExistException {

        return new ResponseEntity<>(testimonialService.save(testimonialRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Testimonial> update(@Valid @RequestBody TestimonialRequest testimonialRequest, @PathVariable Long id) throws NotFoundException, DataAlreadyExistException {
       
    	return new ResponseEntity<>(testimonialService.updateTestimonial(id, testimonialRequest), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws NotFoundException {
       
    	testimonialService.delete(id);
       
    	return new ResponseEntity<>("Testimonial has been deleted", HttpStatus.OK);
    }
    
    @GetMapping
    public ResponseEntity<List<TestimonialDto>> getPage(@RequestParam (defaultValue = "0") Integer page,
    		@RequestParam (defaultValue = "10") Integer sizePage ,@RequestParam (defaultValue = "id") String sortBy){
        
        return new ResponseEntity<>(testimonialService.getPage(page,sizePage,sortBy), HttpStatus.OK);
    }

}
