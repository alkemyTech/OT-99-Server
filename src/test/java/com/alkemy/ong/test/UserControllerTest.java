package com.alkemy.ong.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.alkemy.ong.dto.UserRegisterRequest;
import com.alkemy.ong.dto.UserRegisterResponse;
import com.alkemy.ong.dto.UsersDto;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.Role;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.service.UserService;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserControllerTest {

	@MockBean
	private UserService userService;
	
	@Autowired
	WebApplicationContext context;
	
	private MockMvc mockMvc;
	private UsersDto userDto;
	private List<UsersDto> usersDtoList;
	private Users user;
	private List<Users> usersList;
	private UserRegisterResponse response;
	private UserRegisterRequest request;
	private String jsonUser;
	
	@BeforeEach
	void setup() {

		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
		
		jsonUser="{\"firstName\":\"mariano\",\"lastName\":\"Lopez\",\"email\":\"mariano@gmail.com\",\"password\":\"123456\",\"photo\":null}";
		
		Role role = new Role();
		role.setId(1L);
		role.setName("ADMIN");

		userDto = new UsersDto();
		userDto.setFirstName("federico");
		userDto.setRole(role);
		userDto.setDeleted(false);
		userDto.setPhoto("imagen/1.jpg");
		usersDtoList = new ArrayList<>();

		usersDtoList.add(userDto);

		user = new Users();
		user.setId(1L);
		user.setDeleted(false);
		user.setEmail("pablo@gmail.com");
		user.setFirstName("pablo");
		user.setLastName("amado");
		user.setPassword("123456");
		user.setPhoto("imagen/1.jpg");
		user.setRole(role);

		usersList = new ArrayList<>();
		usersList.add(user);
		
		response= new UserRegisterResponse();
		response.setEmail("mariano@gmail.com");
		response.setFirstName("mariano");
		response.setLastName("Lopez");
		response.setId(1L);
		
		request=new UserRegisterRequest();
		request.setEmail("mariano@gmail.com");
		request.setFirstName("mariano");
		request.setLastName("Lopez");
		request.setPassword("123456");
		request.setPhoto(null);
		
	}

	@Test
	@WithMockUser(username = "admin", roles = { "ADMIN" })
	public void itShouldReturnListOfUsersDto() throws Exception {

		Mockito.when(userService.getAllUsers()).thenReturn(usersDtoList);
		
		mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$[0]").exists())
				.andExpect(jsonPath("$[0].firstName").isString())
				.andExpect(jsonPath("$[0].firstName", is(userDto.getFirstName())));

	}

	@Test
	@WithMockUser(username = "admin", roles = { "ADMIN" })
	public void itShouldReturnAnEmptyListOfUsersDto() throws Exception {

		Mockito.when(userService.getAllUsers()).thenReturn(new ArrayList<UsersDto>());

		mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$[0]").doesNotExist())
				.andExpect(jsonPath("$[0].firstName").doesNotExist());

	}

	@Test
	@WithMockUser(username = "user", roles = { "USER" })
	public void itShouldNotReturnAListAndReturnforbiddenStatusBecauseRoleUser() throws Exception {

		mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isForbidden())
				.andExpect(jsonPath("$").doesNotExist());

	}
	
	@Test
	@WithMockUser(username = "admin", roles = { "ADMIN,USER" })
	public void itShouldDeleteAUser() throws Exception {
		
		mockMvc.perform(delete("/users/{id}",1L).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", is("El usuario fue eliminado con exito")));

	}
	
	@Test
	@WithMockUser(username = "user", roles = { "ADMIN,USER" })
	public void itShouldNotDeleteAnUserAndRaiseNotFoundException() throws Exception {
		
		Mockito.doThrow(new NotFoundException("The user is not registered.")).when(userService).delete(1L);
		
		mockMvc.perform(delete("/users/{id}",1L).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound())
		.andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundException))
	    .andExpect(result -> assertEquals("The user is not registered.", result.getResolvedException().getMessage()));
	
	}
	
	@Test
	@WithMockUser(username = "user", roles = { "ADMIN,USER" })
	public void itShouldUpgradeUser() throws Exception {
			
		Mockito.when(userService.upgradeUser(1L, request)).thenReturn(response);
		
		mockMvc.perform(put("/users/{id}",1L)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonUser))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName", is(response.getFirstName())))
				.andExpect(jsonPath("$.lastName", is(response.getLastName())))
				.andExpect(jsonPath("$.email", is(response.getEmail())))
				.andExpect(jsonPath("$.id", is(Integer.valueOf(response.getId().toString()))));
	}
	
	
	@Test
	@WithMockUser(username = "user", roles = { "ADMIN,USER" })
	public void itShouldNotUpgradeUserAndRaiseException() throws Exception {
		
		Mockito.when(userService.upgradeUser(1L, request)).thenThrow(new NotFoundException("The user is not registered."));
		
				mockMvc.perform(put("/users/{id}",1L)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonUser))
				.andExpect(status().isNotFound())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundException))
				.andExpect(result -> assertEquals("The user is not registered.", result.getResolvedException().getMessage()));
		
	}

}
