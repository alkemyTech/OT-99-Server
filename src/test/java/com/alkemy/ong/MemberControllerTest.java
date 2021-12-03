package com.alkemy.ong;

import com.alkemy.ong.model.Member;
import com.alkemy.ong.repository.MemberRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {

    Member memberMock1;

    Member memberMockWithoutName;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberRepository memberRepository;

    @BeforeEach
    public void setUp() {
        memberMock1 = new Member();
        memberMock1.setId(1l);
        memberMock1.setName("member1");
        memberMock1.setFacebook("htt´://facebook.member1");
        memberMock1.setInstagram("htt´://instagram.member1");
        memberMock1.setLinkedin("htt´://linkedin.member1");
        memberMock1.setImage("imagenMember1");
        memberMock1.setDescription("descripcionMember1");
        memberMock1.setDeleted(false);
        memberMock1.setCreationDate(null);
        memberMock1.setUpdatedDate(null);

        memberMockWithoutName = new Member();
        memberMockWithoutName.setName("");
        memberMockWithoutName.setFacebook("htt´://facebook.member1");
        memberMockWithoutName.setInstagram("htt´://instagram.member1");
        memberMockWithoutName.setLinkedin("htt´://linkedin.member1");
        memberMockWithoutName.setImage("imagenMember1");
        memberMockWithoutName.setDescription("descripcionMember1");
        memberMockWithoutName.setDeleted(false);
        memberMockWithoutName.setCreationDate(null);
        memberMockWithoutName.setUpdatedDate(null);
    }

    @Test
    @WithMockUser(username = "mariano@gmail.com", authorities = {"ROLE_ADMIN"})
    public void add_member_ok() throws Exception {

        String url = "/members";

        Assertions.assertDoesNotThrow(() -> {
            mapToJSON(memberMock1);
        });

        String JSONRequest = mapToJSON(memberMock1);

        mockMvc.perform(post(url)
                .content(JSONRequest)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "mariano@gmail.com", authorities = {"ROLE_ADMIN"})
    public void add_member_bad_request() throws Exception {

        String url = "/members";

        Assertions.assertDoesNotThrow(() -> {
            mapToJSON(memberMockWithoutName);
        });

        String JSONRequest = mapToJSON(memberMockWithoutName);

        mockMvc.perform(post(url)
                .content(JSONRequest)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "mariano@gmail.com", authorities = {"ROLE_ADMIN"})
    public void get_member_ok() throws Exception {

        String url = "/members";

        mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "mariano@gmail.com", authorities = {"ROLE_ADMIN"})
    public void update_member_ok() throws Exception {

        String url = "/members/{id}";

        MultiValueMap<String, String> params =  new LinkedMultiValueMap<>();

        params.add("name", memberMock1.getName());
        params.add("facebook",memberMock1.getFacebook());
        params.add("instagram",memberMock1.getInstagram());
        params.add("linkedin",memberMock1.getLinkedin());
        params.add("image",memberMock1.getImage());
        params.add("description",memberMock1.getDescription());

        given(memberRepository.findById(memberMock1.getId())).willReturn(Optional.of(memberMock1));

        mockMvc.perform(put(url,memberMock1.getId()).queryParams(params)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "mariano@gmail.com", authorities = {"ROLE_ADMIN"})
    public void update_member_bad_request() throws Exception {

        String url = "/members/{id}";

        MultiValueMap<String, String> params =  new LinkedMultiValueMap<>();

        params.add("name", memberMock1.getName());
        params.add("facebook",memberMock1.getFacebook());
        params.add("instagram",memberMock1.getInstagram());
        params.add("linkedin",memberMock1.getLinkedin());
        params.add("image",memberMock1.getImage());
        params.add("description",memberMock1.getDescription());

        given(memberRepository.findById(memberMock1.getId())).willReturn(Optional.empty());

        mockMvc.perform(put(url,memberMock1.getId()).queryParams(params)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "mariano@gmail.com", authorities = {"ROLE_ADMIN"})
    public void delete_member_ok() throws Exception {

        String url = "/members/{id}";

        given(memberRepository.existsById(memberMock1.getId())).willReturn(Boolean.TRUE);

        mockMvc.perform(delete(url,memberMock1.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "mariano@gmail.com", authorities = {"ROLE_ADMIN"})
    public void delete_member_bad_request() throws Exception {

        String url = "/members/{id}";

        given(memberRepository.existsById(memberMock1.getId())).willReturn(Boolean.FALSE);

        mockMvc.perform(delete(url,memberMock1.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    private String mapToJSON(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
}