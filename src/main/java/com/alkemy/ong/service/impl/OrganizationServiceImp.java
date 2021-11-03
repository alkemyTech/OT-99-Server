package com.alkemy.ong.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.alkemy.ong.dto.OrganizationDetailsResponse;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.service.OrganizationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImp implements OrganizationService {

    @Autowired
    OrganizationRepository organizationRepo;

    @Override
    public OrganizationDetailsResponse getOrganizationDetails() throws EntityNotFoundException {

        List<Organization> org = organizationRepo.findAll();

        if (org.isEmpty()) {
            throw new EntityNotFoundException("Organization could not be found.");
        }

        return OrganizationDetailsResponse.mapToResponse(org.get(0));
    }
}
