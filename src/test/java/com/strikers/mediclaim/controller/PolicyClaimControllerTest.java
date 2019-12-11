package com.strikers.mediclaim.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;

import com.strikers.mediclaim.dto.PolicyClaimRequestDto;
import com.strikers.mediclaim.dto.PolicyClaimResponseDto;
import com.strikers.mediclaim.entity.PolicyClaim;
import com.strikers.mediclaim.exception.PolicyNumberNotFoundException;
import com.strikers.mediclaim.service.PolicyClaimService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PolicyClaimControllerTest {

	@InjectMocks
	PolicyClaimController policyClaimController;
	
	@Mock
	PolicyClaimService policyClaimService;
	
	static PolicyClaim policyClaim=new PolicyClaim(); 
	static PolicyClaimRequestDto policyClaimRequestDto=new PolicyClaimRequestDto();
	static PolicyClaimResponseDto policyClaimResponseDto=new PolicyClaimResponseDto();
	
	@Test
	public void applyPolicyClaimPositiveTest() throws PolicyNumberNotFoundException {
		BeanUtils.copyProperties(policyClaim, policyClaimRequestDto);
		Mockito.when(policyClaimService.applyPolicyClaim(policyClaimRequestDto)).thenReturn(policyClaimResponseDto);
		HttpStatus statuscode = policyClaimController.applyPolicyClaim(policyClaimRequestDto).getStatusCode();
		assertEquals(HttpStatus.OK, statuscode);
	}
	
	@Test
	public void applyPolicyClaimNegativeTest() throws PolicyNumberNotFoundException {
		Mockito.when(policyClaimService.applyPolicyClaim(policyClaimRequestDto)).thenReturn(null);
		HttpStatus statuscode = policyClaimController.applyPolicyClaim(policyClaimRequestDto).getStatusCode();
		assertEquals(HttpStatus.NOT_FOUND, statuscode);
	}
	
}
