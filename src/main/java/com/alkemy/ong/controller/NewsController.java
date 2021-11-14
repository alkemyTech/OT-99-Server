package com.alkemy.ong.controller;

import com.alkemy.ong.dto.NewsDtoPersist;
import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.service.NewsService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news")
public class NewsController {

	@Autowired
	private NewsService newsService;

	@PostMapping
	public ResponseEntity<NewsDto> save(@Valid @RequestBody NewsDtoPersist newsDto) throws NotFoundException {

		return new ResponseEntity<>(newsService.save(newsDto), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<NewsDto> update(@RequestBody NewsDtoPersist newsDto, @PathVariable Long id)
			throws NotFoundException {

		return new ResponseEntity<>(newsService.update(newsDto, id), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<NewsDto> getById(@PathVariable Long id) throws NotFoundException {

		return new ResponseEntity<>(newsService.getById(id), HttpStatus.OK);
	}

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNew(@PathVariable Long id) throws NotFoundException {
        newsService.deleteNew(id);
        return new ResponseEntity<>("The new was deleted successfuly", HttpStatus.OK);
    }

}
