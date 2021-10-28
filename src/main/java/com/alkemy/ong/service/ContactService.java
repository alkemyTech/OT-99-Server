package com.alkemy.ong.service;

import java.util.*;

import com.alkemy.ong.model.Contact;
import com.alkemy.ong.repository.ContactRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor

public class ContactService {

@Autowired
ContactRepository repository;



    public void createContact(Contact contact) {
        repository.save(contact);
    }







}