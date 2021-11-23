package com.alkemy.ong.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SlideDtoPost {

    @NotBlank(message = "slide image can't be blank")
    public String imageUrl;

    public String text;
    @NotNull(message = "slide order can't be left blank")
    public Integer slideOrder;
}
