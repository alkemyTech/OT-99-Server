package com.alkemy.ong.service.impl;

import java.util.List;
import com.alkemy.ong.dto.OrganizationDetailsResponse;
import com.alkemy.ong.dto.OrganizationRequest;
import com.alkemy.ong.dto.SlideDtoGet;
import com.alkemy.ong.mapper.OrganizationMapper;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.service.OrganizationService;
import com.alkemy.ong.service.SlideService;
import java.io.IOException;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImp implements OrganizationService {

	@Autowired
	OrganizationRepository organizationRepository;

	@Autowired
	SlideService slideService;

	@Autowired
	OrganizationMapper organizationMapper;

	@Override
	public OrganizationDetailsResponse getOrganizationDetails(Long id) {
		
		Organization organization = organizationRepository.getById(id);

		List<SlideDtoGet> organizationSlides = slideService.getAllSlides(organization);

		return organizationMapper.toOrganizationResponse(organization, organizationSlides);
		
	}
	
	@Override
	public Organization registerOrganization(OrganizationRequest orgRequest) throws IOException {

		orgRequest.setUpdateDateTime(LocalDateTime.now());
		orgRequest.setCreationDateTime(LocalDateTime.now());
		Organization organization = organizationMapper.dtoToEntity(orgRequest);
		organizationRepository.save(organization);

		return organization;

	}

}
