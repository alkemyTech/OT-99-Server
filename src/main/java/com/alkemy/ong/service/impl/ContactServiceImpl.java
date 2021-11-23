package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.mapper.ContactMapper;
import com.alkemy.ong.model.Contact;
import com.alkemy.ong.repository.ContactRepository;
import com.alkemy.ong.service.ContactService;

import com.alkemy.ong.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	ContactRepository contactRepository;

	@Autowired
	ContactMapper contactMapper;

	@Autowired
	private EmailService emailService;

	@Override
	public Contact save(ContactDto contactDto) throws IOException {

		emailService.sendContactRegisterEmail(contactDto);
		return contactRepository.save(contactMapper.toContact(contactDto));

	}

	@Override
	public List<ContactDto> getContacts() {

		List<Contact> contacts = contactRepository.findAll();
		return contacts.stream().map(contact -> new ContactDto(contact.getEmail(), contact.getName(),
				contact.getPhone().longValue(), contact.getMessage())).collect(Collectors.toList());

	}

}
