package com.alkemy.ong.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alkemy.ong.dto.OrganizationDetailsResponse;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.service.OrganizationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImp implements OrganizationService {

    @Autowired
    OrganizationRepository organizationRepo;

    @Override
    public List<OrganizationDetailsResponse> getOrganizationDetails() {

        List<OrganizationDetailsResponse> organizations = new ArrayList<>();
        organizationRepo.findAll().stream().forEach(o -> {
            organizations.add(OrganizationDetailsResponse.mapToResponse(o));
        });

        return organizations;
    }
}
