package com.alkemy.ong;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.alkemy.ong.controller.UserController;
import com.alkemy.ong.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)

public class UserControllerUnitTests {
	
	 @Autowired
	 private MockMvc mvc;

	 @MockBean
	 private UserService service;
}
