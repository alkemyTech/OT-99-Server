package com.alkemy.ong.dto;

import lombok.Data;

@Data
public class TestimonialDto {

	  	private Long id;
	    private String name;
	    private String image;
	    private String content;
	    
	    public TestimonialDto(Long id,String name,String image,String content) {
	    	
	    	this.id=id;
	    	this.name=name;
	    	this.image=image;
	    	this.content=content;
	    }
}
