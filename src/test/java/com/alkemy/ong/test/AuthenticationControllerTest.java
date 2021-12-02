package com.alkemy.ong.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
import com.alkemy.ong.dto.UserLoginRequest;
import com.alkemy.ong.dto.UserRegisterRequest;
import com.alkemy.ong.dto.UserRegisterResponse;
import com.alkemy.ong.exception.DataAlreadyExistException;
import com.alkemy.ong.exception.InvalidCredentialsException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.Role;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.service.UserService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthenticationControllerTest {

	@MockBean
	private UserService userService;

	@Autowired
	WebApplicationContext context;

	private MockMvc mockMvc;

	private Users user;
	private UserRegisterResponse response;
	private UserRegisterRequest request;
	private UserLoginRequest loginRequest;
	private String jsonUser;
	private String jsonLogin;

	@BeforeEach
	void setup() {

		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
		
		jsonUser = "{\"firstName\":\"mariano\",\"lastName\":\"Lopez\",\"email\":\"mariano@gmail.com\",\"password\":\"123456\",\"photo\":null}";
		jsonLogin= "{\"email\":\"pepe123@gmail.com\",\"password\":\"12345678\"}";
		Role role = new Role();
		role.setId(1L);
		role.setName("ADMIN");

		loginRequest=new UserLoginRequest();
		loginRequest.setEmail("mariano@gmail.com");
		loginRequest.setPassword("123456");
		
		user = new Users();
		user.setId(1L);
		user.setDeleted(false);
		user.setEmail("pablo@gmail.com");
		user.setFirstName("pablo");
		user.setLastName("amado");
		user.setPassword("123456");
		user.setPhoto("imagen/1.jpg");
		user.setRole(role);

		response = new UserRegisterResponse();
		response.setEmail("mariano@gmail.com");
		response.setFirstName("mariano");
		response.setLastName("Lopez");
		response.setId(1L);
		response.setJwt("token");

		request = new UserRegisterRequest();
		request.setEmail("mariano@gmail.com");
		request.setFirstName("mariano");
		request.setLastName("Lopez");
		request.setPassword("123456");
		request.setPhoto(null);
	}

	@Test
	public void itShouldRegisterAUser() throws Exception {

		Mockito.when(userService.register(request)).thenReturn(response);

		mockMvc.perform(post("/auth/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonUser))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.firstName", is(response.getFirstName())))
				.andExpect(jsonPath("$.lastName", is(response.getLastName())))
				.andExpect(jsonPath("$.email", is(response.getEmail())))
				.andExpect(jsonPath("$.id", is(Integer.valueOf(response.getId().toString()))))
				.andExpect(jsonPath("$.jwt", is(response.getJwt())));

	}
	
	@Test
	public void itShouldNotRegisterAUserAndRaiseAnDataAlreadyExistException() throws Exception {

		Mockito.doThrow(new DataAlreadyExistException("Email already exist.")).when(userService).register(request);
		
		mockMvc.perform(post("/auth/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonUser))
				.andExpect(status().isBadRequest())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof DataAlreadyExistException))
			    .andExpect(result -> assertEquals("Email already exist.", result.getResolvedException().getMessage()));

	}
	
	@Test
	public void itShouldLogin() throws Exception {

		mockMvc.perform(post("/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonLogin))
				.andExpect(status().isOk());

	}
	
	//NO ANDA 
	@Test
	public void itShouldNotLoginAndRaiseANotFoundException() throws Exception {

		Mockito.when(userService.authenticate(any())).thenThrow(new NotFoundException("The user is not registered."));
		
		mockMvc.perform(post("/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonLogin))
				.andExpect(status().isNotFound())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundException))
			    .andExpect(result -> assertEquals("The user is not registered.", result.getResolvedException().getMessage()));

	}
	
	
	@Test
	public void itShouldNotLoginAndRaiseAnInvalidCredentialsException() throws Exception{

		Mockito.when(userService.authenticate(any())).thenThrow(new InvalidCredentialsException("The data entered are invalid."));
		
		mockMvc.perform(post("/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonLogin))
				.andExpect(status().isForbidden())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidCredentialsException))
			    .andExpect(result -> assertEquals("The data entered are invalid.", result.getResolvedException().getMessage()));

	}
	
	@Test
	@WithMockUser(username = "user", roles = { "ADMIN,USER" })
	public void itShouldGetUserLogged() throws Exception {

		Mockito.when(userService.getUserLogged()).thenReturn(user);
		
		mockMvc.perform(get("/auth/me")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(Integer.valueOf(user.getId().toString()))))
				.andExpect(jsonPath("$.firstName", is(user.getFirstName())))
				.andExpect(jsonPath("$.lastName", is(user.getLastName())))
				.andExpect(jsonPath("$.password", is(user.getPassword())))
				.andExpect(jsonPath("$.photo", is(user.getPhoto())))
				.andExpect(jsonPath("$.email", is(user.getEmail())));
	
	}
	
	@Test
	public void itShouldNotGetUserLoggedBecauseUserIsNotLogged() throws Exception {
		
		mockMvc.perform(get("/auth/me")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isForbidden());

	}
	
}
