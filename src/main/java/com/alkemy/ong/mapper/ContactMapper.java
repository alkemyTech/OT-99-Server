package com.alkemy.ong.mapper;

import org.springframework.stereotype.Component;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.model.Contact;

@Component
public class ContactMapper {

	public Contact toContact(ContactDto contactDto) {
		
		Contact contact=new Contact();
		
		contact.setEmail(contactDto.getEmail());
		contact.setMessage(contactDto.getMessage());
		contact.setName(contact.getName());
		contact.setPhone(contact.getPhone());
		
		return contact;	
	}
	
}
