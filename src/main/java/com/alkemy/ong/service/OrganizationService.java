package com.alkemy.ong.service;

import com.alkemy.ong.dto.OrganizationResponse;
import com.alkemy.ong.dto.OrganizationRequest;
import com.alkemy.ong.model.Organization;
import java.io.IOException;

public interface OrganizationService {

    OrganizationResponse getOrganizationDetails(Long id);
    
    Organization registerOrganization(OrganizationRequest orgRequest) throws IOException;

}
