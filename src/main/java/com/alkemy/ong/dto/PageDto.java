package com.alkemy.ong.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class PageDto<T> {

    private String previusPage;
    private List<T> list;
    private String nextPage;
    private Integer totalPages;
    

}
