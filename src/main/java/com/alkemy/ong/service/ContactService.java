package com.alkemy.ong.service;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.model.Contact;

import java.io.IOException;
import java.util.List;

public interface ContactService {

	Contact save(ContactDto contactDto) throws IOException;

	List<ContactDto> getContacts();
    
}
