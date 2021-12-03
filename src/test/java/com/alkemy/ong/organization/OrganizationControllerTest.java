
package com.alkemy.ong.organization;

import com.alkemy.ong.controller.OrganizationController;
import com.alkemy.ong.dto.OrganizationRequest;
import com.alkemy.ong.dto.OrganizationResponse;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.service.OrganizationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.validation.ValidationException;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.mockito.Mockito;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrganizationControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    OrganizationRepository organizationRepository;

    @MockBean
    OrganizationService organizationService;
//
    @Autowired
    OrganizationController organizationController;

    Organization organization;
    Organization organization2;
    OrganizationRequest organizationRequest;
    OrganizationResponse organizationResponse;

    public OrganizationControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() {

    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws IOException {
        mockMvc = MockMvcBuilders.
                webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
        organizationRequest = new OrganizationRequest();
        organizationRequest.setName("OrgName");
        organizationRequest.setEmail("organization@gmail.com");
        organizationRequest.setWelcomeText("blablabla");
        organizationRequest.setImage("image");

        organization = new Organization();
        organization.setId(1L);
        organization.setName(organizationRequest.getName());
        organization.setEmail(organizationRequest.getEmail());
        organization.setWelcomeText(organizationRequest.getWelcomeText());
        organization.setImage(organizationRequest.getImage());
        organization.setCreationDateTime(LocalDateTime.now());
        organization.setUpdateDateTime(LocalDateTime.now());

        organizationResponse = new OrganizationResponse();
        organizationResponse.setName(organization.getName());
        organizationResponse.setAdress(organization.getEmail());
        organizationResponse.setImage(organization.getImage());

        when(organizationService.registerOrganization(organizationRequest)).thenReturn(organization);
        when(organizationService.getOrganizationDetails(1L)).thenReturn(organizationResponse);
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getOrganizationDetails method, of class OrganizationController.
     *
     * @throws java.lang.Exception
     */
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testGetOrganizationDetails() throws Exception {
        Long id = 1L;
        organizationResponse = organizationService.getOrganizationDetails(1L);
        assertThat(organizationResponse.getAdress()).isEqualTo("organization@gmail.com");
        assertThat(organizationResponse.getName()).isEqualTo("OrgName");
        mockMvc
                .perform(get("/organization/public/" + id + "/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("OrgName"))
                .andExpect(jsonPath("adress").value("organization@gmail.com"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testGetOrganizationDetailsThrowEntityNotFound() throws Exception {
        Long id = 1L;
        Mockito.when(organizationService.getOrganizationDetails(1L)).thenThrow(new NotFoundException("Id not exists"));
        mockMvc
                .perform(get("/organization/public/" + id + "/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    /**
     * Test of postOrganization method, of class OrganizationController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testPostOrganizationWithoutAuthentication() throws Exception {

        Organization organizationCreate = organizationService.registerOrganization(organizationRequest);
        assertThat(organizationCreate.getId()).isEqualTo(1L);
        assertThat(organizationCreate.getName()).isEqualTo("OrgName");
        assertThat(organizationCreate.getEmail()).isEqualTo("organization@gmail.com");
        String request = objectMapper.writeValueAsString(organizationRequest);
        mockMvc
                .perform(post("/organization/public")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testPostOrganizationWithoutName() throws Exception {
        String request = "{\"name\":\"\",\"email\":\"organization@gmail.com\",\"image\":\"image\"\"welcometext\":\"blablabla\"}";

        Organization organizationCreate = organizationService.registerOrganization(organizationRequest);
        assertThat(organizationCreate.getId()).isEqualTo(1L);
        assertThat(organizationCreate.getName()).isEqualTo("OrgName");
        assertThat(organizationCreate.getEmail()).isEqualTo("organization@gmail.com");

        mockMvc
                .perform(post("/organization/public")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertThat(result.getResolvedException() instanceof ValidationException));

    }

    @Test
    @WithMockUser(username = "USER", roles = {"USER"})
    public void testPostOrganizationwithoutAuthorization() throws Exception {

        Organization organizationCreate = organizationService.registerOrganization(organizationRequest);
        assertThat(organizationCreate.getId()).isEqualTo(1L);
        assertThat(organizationCreate.getName()).isEqualTo("OrgName");
        assertThat(organizationCreate.getEmail()).isEqualTo("organization@gmail.com");
        String request = objectMapper.writeValueAsString(organizationRequest);
        mockMvc
                .perform(post("/organization/public")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());

    }
}
