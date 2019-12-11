package com.strikers.mediclaim.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strikers.mediclaim.dto.TrackResponseDto;
import com.strikers.mediclaim.entity.Hospital;
import com.strikers.mediclaim.entity.PolicyClaim;
import com.strikers.mediclaim.exception.PolicyClaimNotFoundException;
import com.strikers.mediclaim.repository.HospitalRepository;
import com.strikers.mediclaim.repository.PolicyClaimRepository;
import com.strikers.mediclaim.util.StringConstant;

@Service
public class PolicyClaimServiceImpl implements PolicyClaimService {

	@Autowired
	PolicyClaimRepository policyClaimRepository;

	@Autowired
	HospitalRepository hospitalRepository;

	/**
	 * trackStatus is the method used to track the status of the policy claim by
	 * giving the referenceNumber
	 * 
	 * @throws PolicyClaimNotFoundException
	 */
	@Override
	public TrackResponseDto trackStatus(String referenceNumber) throws PolicyClaimNotFoundException {
		PolicyClaim policyClaim = policyClaimRepository.findByReferenceNumber(referenceNumber);
		TrackResponseDto trackResponseDto = new TrackResponseDto();
		if (policyClaim != null) {
			Hospital hospital = hospitalRepository.findByHospitalId(policyClaim.getHospital().getHospitalId());
			trackResponseDto.setHospitalName(hospital.getHospitalName());
			BeanUtils.copyProperties(policyClaim, trackResponseDto);
			return trackResponseDto;

		} else {
			throw new PolicyClaimNotFoundException(StringConstant.POLICY_CLAIM_NOT_FOUND);
		}
	}

}
