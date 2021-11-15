package com.alkemy.ong.dto;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestimonialRequest {

    @NotBlank(message = "Testimonial must have a name")
    public String name;
    public String image;

    @NotBlank(message = "Content can't be blank")
    public String content;
    public Date lastUpdated;

}
