package com.alkemy.ong.dto;

import javax.validation.constraints.NotBlank;


public class SlideDtoPost {

    @NotBlank(message = "slide image can't be blank")
    private String imageUrl;

    private String text;
    @NotBlank(message = "slide order can't be left blank")
    private Integer slideOrder;
}
