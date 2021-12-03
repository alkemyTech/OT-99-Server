package com.alkemy.ong;

import com.alkemy.ong.controller.ActivityController;
import com.alkemy.ong.dto.ActivityRequest;
import com.alkemy.ong.exception.DataAlreadyExistException;
import com.alkemy.ong.model.Activity;
import com.alkemy.ong.service.ActivityService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import javax.persistence.EntityNotFoundException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ActivityControllerTests {

        @InjectMocks
        ActivityController activityController;
        
        @MockBean
        ActivityService activityService;

        @Autowired
        ObjectMapper mapper;

        @Autowired
        WebApplicationContext context;

        private MockMvc mockMvc;
        
        private Activity response;

        private ActivityRequest request;

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
                                .andExpect(MockMvcResultMatchers.status().isForbidden())
                                .andExpect(jsonPath("$").doesNotExist());

        }

        @Test
        @WithMockUser(username = "admin", roles = { "ADMIN" })
        public void createActivity_dataAlreadyExist() throws Exception {

                Mockito.when(activityService.save(request))
                                .thenThrow(new DataAlreadyExistException("Wrong! the Activity already exist!."));

                String requestJson = mapper.writeValueAsString(request);

                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/activities")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(requestJson);

                mockMvc.perform(mockRequest)
                                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                                .andExpect(result -> assertTrue(result.getResolvedException() instanceof DataAlreadyExistException))
                                .andExpect(result -> assertEquals("Wrong! the Activity already exist!.", result.getResolvedException().getMessage()));
        }

        @Test
        @WithMockUser(username = "admin", roles = { "ADMIN" })
        public void updateActivity_Success() throws Exception {

                Mockito.when(activityService.updateActivity(1L, request)).thenReturn(response);

                String requestJson = mapper.writeValueAsString(request);

                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/activities/{id}", 1L)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(requestJson);

                mockMvc.perform(mockRequest)
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(jsonPath("$.name", CoreMatchers.is(response.getName())))
                                .andExpect(jsonPath("$.content", CoreMatchers.is(response.getContent())))
                                .andExpect(jsonPath("$.image", CoreMatchers.is(response.getImage())))
                                .andExpect(jsonPath("$.updateDateTime", CoreMatchers.is(response.getCreateDateTime())));
        }

        @Test
        @WithMockUser(username = "admin", roles = { "ADMIN" })
        public void updateActivity_NotFound() throws Exception {

                Mockito.when(activityService.updateActivity(1L, request)).thenThrow(new EntityNotFoundException());

                String requestJson = mapper.writeValueAsString(request);

                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/activities/{id}", 1L)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(requestJson);

                mockMvc.perform(mockRequest)
                                .andExpect(MockMvcResultMatchers.status().isNotFound())
                                .andExpect(result -> assertTrue(result.getResolvedException() instanceof EntityNotFoundException))
                                .andExpect(result -> assertEquals(null, result.getResolvedException().getMessage()));

        }

        @Test
        @WithMockUser(username = "admin", roles = { "ADMIN" })
        public void updateActivity_WrongInput() throws Exception {

                String requestJson = "";

                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/activities/{id}", 1L)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(requestJson);

                mockMvc.perform(mockRequest)
                                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        }

}