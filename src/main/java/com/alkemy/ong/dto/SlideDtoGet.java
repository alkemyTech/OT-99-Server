package com.alkemy.ong.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SlideDtoGet {

	private String imageUrl;
	private String text;
	private Integer slideOrder;
	
	 public SlideDtoGet(String imageUrl, String text, Integer slideOrder) {
		this.imageUrl=imageUrl;
		this.text=text;
		this.slideOrder=slideOrder;
	}

}
