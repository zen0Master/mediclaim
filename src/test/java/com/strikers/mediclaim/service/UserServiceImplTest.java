package com.strikers.mediclaim.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.strikers.mediclaim.dto.UserDto;
import com.strikers.mediclaim.entity.PolicyClaim;
import com.strikers.mediclaim.entity.User;
import com.strikers.mediclaim.repository.PolicyClaimRepository;
import com.strikers.mediclaim.repository.UserRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserServiceImplTest {

	@InjectMocks
	UserServiceImpl userServiceImpl;

	@Mock
	UserRepository userRepository;
	@Mock
	PolicyClaimRepository policyClaimRepository;

	User user = new User();
	UserDto userDto = new UserDto();
	PolicyClaim policyClaim = new PolicyClaim();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		user.setUsername("Sujal");
		user.setPassword("sujal@123");
		user.setStatus("approved");
		user.setUserId(1);
		
		userDto.setUsername("test");
		userDto.setPassword("sujal123");

	}

	@Test
	public void testLogin() {
		Mockito.when(userRepository.findByUsernameAndPasswordAndStatus("Sujal", "sujal@123", "Success"))
				.thenReturn(user);
		userServiceImpl.userLogin(userDto);
		assertEquals("approved", user.getStatus());

	}

	@Test
	public void testPolicyClaim() {
		List<PolicyClaim> policyClaimList = new ArrayList<>();
		policyClaimList.add(policyClaim);

		Mockito.when(policyClaimRepository.findAll()).thenReturn(policyClaimList);

	}
}
