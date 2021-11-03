package com.alkemy.ong.service;

import com.alkemy.ong.dto.OrganizationDetailsResponse;

import javax.persistence.EntityNotFoundException;

public interface OrganizationService {

    OrganizationDetailsResponse getOrganizationDetails() throws EntityNotFoundException;

}
