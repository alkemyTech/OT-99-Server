package com.alkemy.ong.contact;

import com.alkemy.ong.controller.ContactController;
import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.model.Contact;
import com.alkemy.ong.repository.ContactRepository;
import com.alkemy.ong.service.impl.ContactServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Arrays;


public class ContactControllerTest {
    @Mock
    private ContactRepository contactRepository;

    @Mock
    private ContactServiceImpl contactService;

    @InjectMocks
    private ContactController contactController;

    private Contact contact;

    private ContactDto contactDto;

    private Contact contact1;

    private ContactDto contactDto1;
    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
        contactDto = new ContactDto("new@gmail.com","Nuevo",123454L,"new message");
        contactDto1 = new ContactDto("pedrolopez@gmail.com","Pedro Lopez",12345L,"new message");
        contact = new Contact();
        contact.setEmail("new@gmail.com");
        contact.setMessage("new message");
        contact.setPhone(123454D);
        contact.setName("Nuevo");
        contact.setId(1L);
        Contact contact1 = new Contact();
        contact1.setPhone(123454D);
        contact1.setName("Nuevo");
        contact1.setEmail("new@gmail.com");
        contact1.setMessage("new message");
    }

    @Test
    void getAllContact(){
        Mockito.when(contactRepository.findAll()).thenReturn(Arrays.asList(contact));
        Mockito.when(contactService.getContacts()).thenReturn(Arrays.asList(contactDto));
        Assertions.assertNotNull(contactController.getContacts());
    }
    @Test
    void getAllContactSize(){
        Mockito.when(contactRepository.findAll()).thenReturn(Arrays.asList(contact));
        Mockito.when(contactService.getContacts()).thenReturn(Arrays.asList(contactDto));
        Assertions.assertNotEquals(Arrays.asList(contactDto, contactDto1).size(),contactController.getContacts().getBody().size());
    }
    @Test
    void getAllContactStatus(){
        Mockito.when(contactRepository.findAll()).thenReturn(Arrays.asList(contact));
        Mockito.when(contactService.getContacts()).thenReturn(Arrays.asList(contactDto));
        Assertions.assertEquals(HttpStatus.OK,contactController.getContacts().getStatusCode());
    }
    @Test
    void saveContactStatus() throws IOException {
        Mockito.when(contactRepository.save(contact1)).thenReturn(contact);
        Mockito.when(contactService.save(contactDto)).thenReturn(contact);
        Assertions.assertEquals(HttpStatus.CREATED,contactController.save(contactDto).getStatusCode());
    }

    @Test
    void saveContact() throws IOException {
        Mockito.when(contactRepository.save(contact1)).thenReturn(contact);
        Mockito.when(contactService.save(contactDto)).thenReturn(contact);
        Assertions.assertEquals(new ResponseEntity<>(contact,HttpStatus.CREATED),contactController.save(contactDto));
    }
}
