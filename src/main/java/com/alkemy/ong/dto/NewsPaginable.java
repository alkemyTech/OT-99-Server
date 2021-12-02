package com.alkemy.ong.dto;

import com.alkemy.ong.model.News;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NewsPaginable {
    private List<News> news;
    private String nextPage;
    private String previousPage;
}
