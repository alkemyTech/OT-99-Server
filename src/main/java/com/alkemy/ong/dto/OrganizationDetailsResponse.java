package com.alkemy.ong.dto;

import com.alkemy.ong.model.Organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrganizationDetailsResponse {
    
    private String name;
    private String image;
    private int phone;
    private String adress;

    public static OrganizationDetailsResponse mapToResponse(Organization organization) {
        OrganizationDetailsResponse o = new OrganizationDetailsResponse();
        o.setName(organization.getName());
        o.setImage(organization.getImage());
        o.setPhone(organization.getPhone());
        o.setAdress(organization.getAddress());
        return o;
    }

}
