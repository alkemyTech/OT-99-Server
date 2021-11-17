package com.alkemy.ong.dto;

import com.alkemy.ong.controller.OrganizationController;
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
    private String facebook;
    private String instagram;
    private String linkedin;
    private int phone;
    private String address;

    public static OrganizationDetailsResponse mapToResponse(Organization organization) {
        OrganizationDetailsResponse o = new OrganizationDetailsResponse();
        o.setName(organization.getName());
        o.setImage(organization.getImage());
        o.setFacebook(organization.getFacebook());
        o.setInstagram(organization.getInstagram());
        o.setLinkedin(organization.getLinkedin());
        o.setPhone(organization.getPhone());
        o.setAddress(organization.getAddress());
        return o;
    }

}
