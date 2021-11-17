package com.alkemy.ong.controller;

import com.alkemy.ong.dto.OrganizationResponse;
import com.alkemy.ong.dto.OrganizationRequest;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.service.OrganizationService;
import java.io.IOException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    OrganizationService organizationService;

    @GetMapping("/public/{id}")
    public ResponseEntity<OrganizationResponse> getOrganizationDetails(@PathVariable Long id) {
    	
        return new ResponseEntity<>(organizationService.getOrganizationDetails(id), HttpStatus.OK);
    }

    @PostMapping("/public")
    public ResponseEntity<Organization> postOrganization(@Valid @RequestBody OrganizationRequest orgRequest) throws IOException {
        
        Organization organization = organizationService.registerOrganization(orgRequest);
        return new ResponseEntity<>(organization, HttpStatus.CREATED);

    }
}
