package com.alkemy.ong.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.model.Contact;
import com.alkemy.ong.service.ContactService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

	@Autowired
	ContactService contactService;
	
	@PostMapping
	public ResponseEntity<Contact> save(@Valid @RequestBody ContactDto contactDto) throws IOException {
		
		return new ResponseEntity<>(contactService.save(contactDto), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<ContactDto>> getContacts(){
		return new ResponseEntity<>(contactService.getContacts(), HttpStatus.OK);		
	} 
}
