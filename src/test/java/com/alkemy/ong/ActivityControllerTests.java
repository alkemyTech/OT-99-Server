package com.alkemy.ong;

import com.alkemy.ong.controller.ActivityController;
import com.alkemy.ong.dto.ActivityRequest;
import com.alkemy.ong.exception.DataAlreadyExistException;
import com.alkemy.ong.model.Activity;
import com.alkemy.ong.service.ActivityService;
import com.amazonaws.services.codebuild.model.SourceType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.assertj.core.api.Assert;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import javax.validation.constraints.AssertTrue;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ActivityControllerTests {

    Logger logger = LoggerFactory.getLogger(ActivityControllerTests.class);

    @InjectMocks
    ActivityController activityController;

    @Autowired
    WebApplicationContext context;

    @Autowired
    ObjectMapper mapper;

    private MockMvc mockMvc;
    private Activity response;
    private ActivityRequest request;
    private String jsonActivity;

    @MockBean
    ActivityService activityService;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();

        this.request = new ActivityRequest();
        request.setContent("La actividad se desarrollo con normalidad");
        request.setImage("com/imgres?imgurl=ht");
        request.setName("Primera actividad");
        request.setUpdateDateTime(null);

        this.response = new Activity();
        response.setContent("La actividad se desarrollo con normalidad");
        response.setDeleted(false);
        response.setId(1L);
        response.setImage("com/imgres?imgurl=ht");
        response.setName("Primera actividad");
        response.setUpdateDateTime(null);

        this.jsonActivity = "{\"name\":\"Primera Actividad\",\"content\":\"La actividad se desarrollo con normalidad\",\"image\":\"com/imgres?imgurl=ht\",\"updateDateTime\":null}";

    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    public void createActivity_Success() throws Exception {

        Mockito.when(activityService.save(request)).thenReturn(response);
        String requestJson = mapper.writeValueAsString(request);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/activities")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestJson);

        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.name", CoreMatchers.is(response.getName())))
                .andExpect(jsonPath("$.content", CoreMatchers.is(response.getContent())))
                .andExpect(jsonPath("$.image", CoreMatchers.is(response.getImage())))
                .andExpect(jsonPath("$.updateDateTime", CoreMatchers.is(response.getCreateDateTime())));

    }

    @Test
    @WithMockUser(username = "user", roles = { "USER" })
    public void createActivity_WrongRole() throws Exception {

        Mockito.when(activityService.save(request)).thenReturn(response);
        String requestJson = mapper.writeValueAsString(request);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/activities")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestJson);

        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isForbidden()).andExpect(jsonPath("$").doesNotExist());

    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    public void createActivity_dataAlreadyExist() throws Exception {

        Mockito.when(activityService.save(request)).thenThrow(new DataAlreadyExistException("Wrong! the Activity already exist!."));
        String requestJson = mapper.writeValueAsString(request);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/activities")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestJson);
        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result ->new AssertTrue(result.getResolvedException() instanceof DataAlreadyExistException))
		.andExpect(result -> assertEquals("The user is not registered.", result.getResolvedException().getMessage()));
;
    }
}


    /*
     * 
     * 
     * @Test
     * 
     * @WithMockUser(username = "admin", roles = { "ADMIN" })
     * public void validCreateRequest() throws Exception {
     * Mockito.when(activityService.save(request)).thenReturn(response);
     * MockHttpServletRequestBuilder mockRequest =
     * MockMvcRequestBuilders.post("/activities")
     * .contentType(MediaType.APPLICATION_JSON)
     * .accept(MediaType.APPLICATION_JSON)
     * .content(jsonActivity);
     * 
     * mockMvc.perform(mockRequest)
     * .andExpect(status().isCreated())
     * .andExpect(jsonPath("$.name", CoreMatchers.is(response.getName())))
     * .andExpect(jsonPath("$.content", CoreMatchers.is(response.getContent())))
     * .andExpect(jsonPath("$.image", CoreMatchers.is(response.getImage())))
     * .andExpect(jsonPath("$.updateDateTime",
     * CoreMatchers.is(response.getCreateDateTime())));
     * 
     * 
     * }
     */

