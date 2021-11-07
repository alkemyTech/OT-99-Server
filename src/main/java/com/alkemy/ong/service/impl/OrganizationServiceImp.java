package com.alkemy.ong.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.alkemy.ong.dto.OrganizationDetailsResponse;
import com.alkemy.ong.dto.OrganizationRequest;
import com.alkemy.ong.mapper.OrganizationMapper;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.service.OrganizationService;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImp implements OrganizationService {

    @Autowired
    OrganizationRepository organizationRepo;
    @Autowired
    OrganizationMapper organizationMapper;

    @Override
    public List<OrganizationDetailsResponse> getOrganizationDetails() {

        List<OrganizationDetailsResponse> organizations = organizationRepo.findAll().stream()
                .map(OrganizationDetailsResponse::mapToResponse).collect(Collectors.toList());
        return organizations;
    }

    @Override
    public Organization registerOrganization(OrganizationRequest orgRequest) {
        orgRequest.setUpdateDateTime(LocalDateTime.now());
        orgRequest.setCreationDateTime(LocalDateTime.now());
        Organization organization = organizationMapper.dtoToEntity(orgRequest);
        organizationRepo.save(organization);
        return organization;
        
    }

}
