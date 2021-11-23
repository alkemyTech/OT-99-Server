package com.alkemy.ong.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class SlideDtoUpdate {

    private Long id;
    private String imageUrl;
    private String text;
    private Integer slideOrder;
    private Long organizationId;
    
}
