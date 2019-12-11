package com.strikers.mediclaim.service;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.strikers.mediclaim.dto.TrackResponseDto;
import com.strikers.mediclaim.entity.Hospital;
import com.strikers.mediclaim.entity.PolicyClaim;
import com.strikers.mediclaim.exception.PolicyClaimNotFoundException;
import com.strikers.mediclaim.repository.HospitalRepository;
import com.strikers.mediclaim.repository.PolicyClaimRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PolicyClaimServiceTest {

	@InjectMocks
	PolicyClaimServiceImpl policyClaimServiceImpl;

	@Mock
	PolicyClaimRepository policyClaimRepository;

	@Mock
	HospitalRepository hospitalRepository;

	PolicyClaim policyClaim = new PolicyClaim();
	TrackResponseDto trackResponseDto = new TrackResponseDto();
	Hospital hospital = new Hospital();

	@Before
	public void setUp() {
		policyClaim.setPolicyClaimId(1);
		policyClaim.setReferenceNumber("REF10");
		policyClaim.setName("Shakthi");
		policyClaim.setPolicyNumber("P123");
		policyClaim.setDiagnosis("Laser");
		policyClaim.setAdmissionDate(LocalDate.of(2019, 12, 10));
		policyClaim.setDischargeDate(LocalDate.of(2019, 12, 11));
		;
		policyClaim.setClaimAmount(12000.0);
		policyClaim.setHospitalId(1);
		policyClaim.setDischargeSummary("Recovered");
		policyClaim.setNatureOfAilment("Eye Operation");
		policyClaim.setClaimStatus("Pending");
		policyClaim.setCreatedDate(LocalDate.of(2019, 12, 11));

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

		hospital.setHospitalId(1);
		hospital.setHospitalName("Aravind");
		hospital.setAddress("Pondicherry");
	}

	@Test
	public void testTrackStatusPositive() throws PolicyClaimNotFoundException {
		Mockito.when(policyClaimRepository.findByReferenceNumber("REF10")).thenReturn(policyClaim);
		Mockito.when(hospitalRepository.findByHospitalId(1)).thenReturn(hospital);
		policyClaimServiceImpl.trackStatus("REF10");
	}

	@Test(expected = PolicyClaimNotFoundException.class)
	public void testTrackStatusNegative() throws PolicyClaimNotFoundException {
		Mockito.when(policyClaimRepository.findByReferenceNumber("REF10")).thenReturn(policyClaim);
		Mockito.when(hospitalRepository.findByHospitalId(1)).thenReturn(hospital);
		policyClaimServiceImpl.trackStatus("REF11");
	}

}
