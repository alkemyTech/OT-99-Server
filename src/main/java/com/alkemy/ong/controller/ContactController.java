package com.alkemy.ong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.service.ContactService;

@RestController
@RequestMapping("/contacts")
public class ContactController {

	@Autowired
	ContactService contactService;
	
	@PostMapping
	public ResponseEntity<ContactDto> save(@RequestBody ContactDto contactDto){
		
		return new ResponseEntity<>(contactService.save(contactDto), HttpStatus.CREATED);
	}
}
