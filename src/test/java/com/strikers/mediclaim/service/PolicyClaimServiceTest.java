package com.strikers.mediclaim.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;

import com.strikers.mediclaim.dto.PolicyClaimRequestDto;
import com.strikers.mediclaim.dto.PolicyClaimResponseDto;
import com.strikers.mediclaim.entity.Policy;
import com.strikers.mediclaim.entity.PolicyClaim;
import com.strikers.mediclaim.exception.PolicyNumberNotFoundException;
import com.strikers.mediclaim.repository.PolicyClaimRepository;
import com.strikers.mediclaim.repository.PolicyRepository;
import com.strikers.mediclaim.util.StringConstant;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PolicyClaimServiceTest {

	@InjectMocks
	PolicyClaimServiceImpl policyClaimService;
	
	@Mock
	PolicyClaimRepository policyClaimRepository;
	
	@Mock
	PolicyRepository policyRepository;
	
	static PolicyClaim policyClaim=new PolicyClaim(); 
	static PolicyClaimRequestDto policyClaimRequestDto=new PolicyClaimRequestDto();
	static PolicyClaimResponseDto policyClaimResponseDto=new PolicyClaimResponseDto();
	static Optional<Policy> policy=Optional.empty();
	static Policy policies=new Policy();
	
	@Test(expected = PolicyNumberNotFoundException.class)
	public void applyPolicyClaimNegativeTest() throws PolicyNumberNotFoundException {
		BeanUtils.copyProperties(policyClaim, policyClaimRequestDto);
		policies.setPolicyNumber("12345");
		Mockito.when(policyRepository.findByPolicyNumber(policyClaim.getPolicyNumber())).thenReturn(policy);
		policyClaim.setReferenceNumber("MC12345");
		policyClaim.setCreatedDate(LocalDate.of(2019, 10, 11));
		policyClaim.setClaimStatus("Pending");
		PolicyClaimResponseDto PolicyClaimResponseDtos= policyClaimService.applyPolicyClaim(policyClaimRequestDto);
		assertEquals(StringConstant.FAILURE, PolicyClaimResponseDtos.getMessage());
	}
	
	@Test(expected = PolicyNumberNotFoundException.class)
	public void applyPolicyClaimPositiveTest() throws PolicyNumberNotFoundException {
		BeanUtils.copyProperties(policyClaim, policyClaimRequestDto);
		policies.setPolicyNumber("12345");
		Mockito.when(policyRepository.findByPolicyNumber(policyClaim.getPolicyNumber())).thenReturn(policy);
		policyClaim.setReferenceNumber("MC12345");
		policyClaim.setCreatedDate(LocalDate.of(2019, 10, 11));
		policyClaim.setClaimStatus("Pending");
		PolicyClaimResponseDto PolicyClaimResponseDtos= policyClaimService.applyPolicyClaim(policyClaimRequestDto);
		assertEquals(StringConstant.FAILURE, PolicyClaimResponseDtos.getMessage());
	}
}
