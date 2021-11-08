
package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.OrganizationRequest;
import com.alkemy.ong.model.Organization;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationMapper {
    
@Autowired
ModelMapper modelMapper;

public Organization dtoToEntity(OrganizationRequest orgDto){
    Organization organization = modelMapper.map(orgDto, Organization.class);
    return organization;
}



}
