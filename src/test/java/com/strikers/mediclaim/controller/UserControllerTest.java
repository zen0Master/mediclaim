package com.strikers.mediclaim.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.strikers.mediclaim.entity.User;
import com.strikers.mediclaim.service.UserService;
import com.strikers.mediclaim.util.StringConstant;



@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {
	
	//private static final Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

	@InjectMocks
	private UserController userController;

	@Mock
	private UserService userService;

	User user = new User();
	
	@Before
	public void setup() {
		user.setUserId(123);
		user.setUsername("test");
		user.setPassword("sujal123");
		user.setRole(StringConstant.APPROVER_ROLE);
	}
	
	@Test
	public void testLoginUserNegative() {
		Mockito.when(userService.userLogin(user)).thenReturn(user);
		Integer result = userController.userLogin(user).getStatusCodeValue();
		assertEquals(200, result);
	}
	
	@Test
	public void testSaveUserNegative(){
		Mockito.when(userService.userLogin(null)).thenReturn(null);
		Integer result = userController.userLogin(null).getStatusCodeValue();
		assertEquals(400, result);
	}
}
