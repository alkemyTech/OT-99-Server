package com.alkemy.ong.dto;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonInclude;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDetailsDto {

    private String name;
    private String description;
    private String image;
    private LocalDateTime creationDate;
    private LocalDateTime updatedAt;
}
