package com.alkemy.ong.test;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import com.alkemy.ong.model.Users;


class UserControllerTest {
	 
	 @Test
	 public void givenUsers_whenGetUsers_thenReturnUserJsonList() throws Exception {
		 
		 Users firstUser=new Users();
		 firstUser.setId(1L);
		 List<Users> userList=new ArrayList<>();
		 userList.add(firstUser);
		 
	
		
	 }

}
