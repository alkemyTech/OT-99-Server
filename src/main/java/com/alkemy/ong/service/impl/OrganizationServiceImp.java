package com.alkemy.ong.service.impl;

import java.util.List;
import java.util.stream.Collectors;

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

        List<OrganizationDetailsResponse> organizations = organizationRepo.findAll().stream()
                .map(OrganizationDetailsResponse::mapToResponse).collect(Collectors.toList());
        return organizations;
    }
}
