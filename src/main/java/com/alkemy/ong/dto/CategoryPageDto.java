package com.alkemy.ong.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class CategoryPageDto {

    private String previusPage;
    private List<CategoryDtoGetAll> list;
    private String nextPage;

}
