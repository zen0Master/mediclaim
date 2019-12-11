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
import com.strikers.mediclaim.service.PolicyClaimService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PolicyClaimControllerTest {

	@InjectMocks
	PolicyClaimController policyClaimController;

	@Mock
	PolicyClaimService policyClaimService;

	static TrackResponseDto trackResponseDto = new TrackResponseDto();

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

}
