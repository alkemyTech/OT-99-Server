package com.alkemy.ong.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrganizationResponse {
    
    private String name;
    private String image;
    private int phone;
    private String adress;
    private List<SlideDtoGet> organizationSlides;
	
}
