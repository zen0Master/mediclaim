package com.strikers.mediclaim.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.strikers.mediclaim.dto.UserDto;
import com.strikers.mediclaim.entity.PolicyClaim;
import com.strikers.mediclaim.entity.User;
import com.strikers.mediclaim.service.UserService;
import com.strikers.mediclaim.util.StringConstant;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {
	
	@InjectMocks
	private UserController userController;

	@Mock
	private UserService userService;

	User user = new User();
	UserDto userDto = new UserDto();
	
	@Before
	public void setup() {
		user.setUserId(123);
		user.setUsername("test");
		user.setPassword("sujal123");
		user.setRole(StringConstant.APPROVER_ROLE);
		
		userDto.setUsername("test");
		userDto.setPassword("sujal123");
	}
	
	@Test
	public void testLoginUserPositive() {
		Mockito.when(userService.userLogin(userDto)).thenReturn(user);
		Integer result = userController.userLogin(userDto).getStatusCodeValue();
		assertEquals(200, result);
	}
	
	@Test
	public void testSaveUserNegative(){
		Mockito.when(userService.userLogin(null)).thenReturn(null);
		Integer result = userController.userLogin(null).getStatusCodeValue();
		assertEquals(400, result);
	}
	
	@Test
	public void testClaimPositive() {
		Integer userId=234;
		PolicyClaim policyClaim= new PolicyClaim();
		List<PolicyClaim> list = new ArrayList<>();
		list.add(policyClaim);
		Mockito.when(userService.policyClaims(userId)).thenReturn(list);
		
		Integer result = userController.policyClaims(userId).getStatusCodeValue();
		assertEquals(200, result);
	}
	
	@Test
	public void testClaimNegative(){
		Integer userId=null;
		Mockito.when(userService.policyClaims(userId)).thenReturn(null);
		
		Integer result = userController.policyClaims(userId).getStatusCodeValue();
		assertEquals(204, result);
	}
}
