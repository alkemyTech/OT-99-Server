package com.alkemy.ong.controller;

import javax.persistence.EntityNotFoundException;

import com.alkemy.ong.service.OrganizationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    OrganizationService organizationService;

    @GetMapping("/public")
    public ResponseEntity<?> getOrganizationDetails() throws EntityNotFoundException {
        return new ResponseEntity<>(organizationService.getOrganizationDetails(), HttpStatus.OK);
    }

}
