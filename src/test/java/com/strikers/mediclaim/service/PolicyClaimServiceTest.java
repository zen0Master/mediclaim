package com.strikers.mediclaim.service;

import java.time.LocalDate;

import org.junit.Before;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Optional;

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
	PolicyClaimServiceImpl policyClaimServiceImpl;

	@Mock
	PolicyClaimRepository policyClaimRepository;

	@Mock
	HospitalRepository hospitalRepository;

	@Mock
	PolicyRepository policyRepository;
	
	PolicyClaim policyClaim = new PolicyClaim();
	TrackResponseDto trackResponseDto = new TrackResponseDto();
	Hospital hospital = new Hospital();
	static PolicyClaimRequestDto policyClaimRequestDto=new PolicyClaimRequestDto();
	static PolicyClaimResponseDto policyClaimResponseDto=new PolicyClaimResponseDto();
	static Optional<Policy> policy=Optional.empty();
	static Policy policies=new Policy();

	@Before
	public void setUp() {
		hospital.setHospitalId(1);
		hospital.setHospitalName("Aravind");
		hospital.setAddress("Pondicherry");
		
		policyClaim.setPolicyClaimId(1);
		policyClaim.setReferenceNumber("REF10");
		policyClaim.setName("Shakthi");
		policyClaim.setPolicyNumber("P123");
		policyClaim.setDiagnosis("Laser");
		policyClaim.setAdmissionDate(LocalDate.of(2019, 12, 10));
		policyClaim.setDischargeDate(LocalDate.of(2019, 12, 11));
		policyClaim.setClaimAmount(12000.0);
		policyClaim.setHospital(hospital);
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
	
	
	@Test(expected = PolicyNumberNotFoundException.class)
	public void applyPolicyClaimNegativeTest() throws PolicyNumberNotFoundException {
		BeanUtils.copyProperties(policyClaim, policyClaimRequestDto);
		policies.setPolicyNumber("12345");
		Mockito.when(policyRepository.findByPolicyNumber(policyClaim.getPolicyNumber())).thenReturn(policy);
		policyClaim.setReferenceNumber("MC12345");
		policyClaim.setCreatedDate(LocalDate.of(2019, 10, 11));
		policyClaim.setClaimStatus("Pending");
		policyClaim.setHospital(hospital);
		PolicyClaimResponseDto PolicyClaimResponseDtos= policyClaimServiceImpl.applyPolicyClaim(policyClaimRequestDto);
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
		policyClaim.setHospital(hospital);
		PolicyClaimResponseDto PolicyClaimResponseDtos= policyClaimServiceImpl.applyPolicyClaim(policyClaimRequestDto);
		assertEquals(StringConstant.SUCCESS, PolicyClaimResponseDtos.getMessage());
	}
}
