package com.alkemy.ong.controller;

import java.util.*;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.dto.NewsDtoPersist;
import com.alkemy.ong.mapper.NewsMapper;
import com.alkemy.ong.model.News;
import com.alkemy.ong.controller.NewsController;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.CommentService;
import com.alkemy.ong.service.NewsService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.BDDMockito.*;
import org.springframework.http.MediaType;
import static org.springframework.http.MediaType.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.alkemy.ong.exception.*;

import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest

public class NewsControllerTest {

    @Autowired
    WebApplicationContext context;

    MockMvc mockMvc;

    @MockBean
    private NewsService newsService;

    @MockBean 
    NewsRepository newsRepository;

    @MockBean
    CommentService commentService;

    @Autowired
    NewsController newsController;

    @Autowired
    NewsMapper newsMapper;

    @Autowired
    ObjectMapper objectMapper;

    private static News news;
    private static NewsDto newsDto;
    private static NewsDtoPersist newsDtoPersist;
    private static News news2;
    private static NewsDto newsDto2;

    @BeforeEach
    void setUp() throws Exception {
        mockMvc = MockMvcBuilders.
                webAppContextSetup(context)
                .apply(springSecurity())
                .build();
        news = new News();
        news.setId(1L);
        news.setName("Technology");
        news.setContent("TechnologyContent");
        news.setImage("ImageTech");

        newsDto = new NewsDto();
        newsDto.setName("Biotechnology");
        newsDto.setContent("aboutbiotechnology");

        newsDto2 = new NewsDto();
        newsDto2.setName("Science");
        newsDto2.setContent("Sciencedescription");

        news2 = new News();
        news2.setId(2L);
        news2.setName(newsDto.getName());
        news2.setContent(newsDto.getContent());

        Mockito.when(newsService.save(newsDtoPersist)).thenReturn(newsDto);
        
    }

    @Test
    @WithAnonymousUser
    void testDeleteNew_StatusCode403() throws Exception{
       mockMvc.perform(delete("/news/{id}", 1L)
                .contentType(TEXT_PLAIN))
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testGetById() throws Exception {
        Long id = 1L;
        when(newsService.getById(1L)).thenReturn(newsDto);
        mockMvc.perform(get("/news/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("name").value("Biotechnology"))
        .andExpect(jsonPath("content").value("aboutbiotechnology"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testGetComment() throws Exception {
        when(commentService.getCommentbyNewsId(2L)).thenThrow(new NotFoundException("ID not found"));
         mockMvc.perform(get("/news/{id}/comments",2L)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "USER", roles = {"USER"})
    void testSave() throws Exception{
        mockMvc.perform(post("/news")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testUpdate() throws Exception{
        mockMvc.perform(put("/news/{id}", 1L)
                .content(objectMapper.writeValueAsString(null))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
}
