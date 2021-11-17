package com.alkemy.ong.service;

import com.alkemy.ong.dto.OrganizationDetailsResponse;
import com.alkemy.ong.dto.OrganizationRequest;
import com.alkemy.ong.model.Organization;
import java.io.IOException;

public interface OrganizationService {

    OrganizationDetailsResponse getOrganizationDetails(Long id);
    
    Organization registerOrganization(OrganizationRequest orgRequest) throws IOException;

}
