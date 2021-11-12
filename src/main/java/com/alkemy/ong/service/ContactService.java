package com.alkemy.ong.service;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.model.Contact;

public interface ContactService {

	Contact save(ContactDto contactDto);
    
}
