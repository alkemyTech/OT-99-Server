package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.OrganizationResponse;
import com.alkemy.ong.dto.OrganizationRequest;
import com.alkemy.ong.dto.SlideDtoGet;
import com.alkemy.ong.model.Organization;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMapper {

	@Autowired
	ModelMapper modelMapper;

	public Organization dtoToEntity(OrganizationRequest orgDto) {

		Organization organization = modelMapper.map(orgDto, Organization.class);

		return organization;
	}

	public OrganizationResponse toOrganizationResponse(Organization organization,
			List<SlideDtoGet> organizationSlides) {

		OrganizationResponse organizationResponse = new OrganizationResponse();
		organizationResponse.setName(organization.getName());
		organizationResponse.setImage(organization.getImage());
		organizationResponse.setPhone(organization.getPhone());
		organizationResponse.setAdress(organization.getAddress());
		organizationResponse.setOrganizationSlides(organizationSlides);
		
		return organizationResponse;

	}

}
