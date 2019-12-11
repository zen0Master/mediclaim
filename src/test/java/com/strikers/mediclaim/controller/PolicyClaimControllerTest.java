package com.strikers.mediclaim.controller;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.strikers.mediclaim.dto.TrackResponseDto;
import com.strikers.mediclaim.exception.PolicyClaimNotFoundException;
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

	static TrackResponseDto trackResponseDto = new TrackResponseDto();
	static PolicyClaim policyClaim=new PolicyClaim(); 
	static PolicyClaimRequestDto policyClaimRequestDto=new PolicyClaimRequestDto();
	static PolicyClaimResponseDto policyClaimResponseDto=new PolicyClaimResponseDto();

	@Before
	public void setUp() {
		trackResponseDto.setReferenceNumber("REF10");
		trackResponseDto.setName("Shakthi");
		trackResponseDto.setPolicyNumber("P123");
		trackResponseDto.setDiagnosis("Laser");
		trackResponseDto.setAdmissionDate(LocalDate.of(2019, 12, 10));
		trackResponseDto.setDischargeDate(LocalDate.of(2019, 12, 11));
		trackResponseDto.setClaimAmount(12000.0);
		trackResponseDto.setHospitalName("Aravind");
		trackResponseDto.setDischargeSummary("Recovered");
		trackResponseDto.setNatureOfAilment("Eye Operation");
		trackResponseDto.setClaimStatus("Pending");
		trackResponseDto.setCreatedDate(LocalDate.of(2019, 12, 11));
	}

	@Test
	public void testTrackStatusPositive() throws PolicyClaimNotFoundException {
		Mockito.when(policyClaimService.trackStatus("REF10")).thenReturn(trackResponseDto);
		Integer result = policyClaimController.trackStatus("REF10").getStatusCodeValue();
		assertEquals(200, result);
	}

	@Test
	public void testTrackStatusNegative() throws PolicyClaimNotFoundException {
		Mockito.when(policyClaimService.trackStatus("REF10")).thenReturn(trackResponseDto);
		Integer result = policyClaimController.trackStatus("REF11").getStatusCodeValue();
		assertEquals(204, result);
	}

	
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
