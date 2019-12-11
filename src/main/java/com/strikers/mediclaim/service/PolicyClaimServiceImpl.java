package com.strikers.mediclaim.service;

import java.time.LocalDate;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.strikers.mediclaim.dto.PolicyClaimRequestDto;
import com.strikers.mediclaim.dto.PolicyClaimResponseDto;
import com.strikers.mediclaim.dto.TrackResponseDto;
import com.strikers.mediclaim.entity.Hospital;
import com.strikers.mediclaim.entity.Policy;
import com.strikers.mediclaim.entity.PolicyClaim;
import com.strikers.mediclaim.exception.PolicyClaimNotFoundException;
import com.strikers.mediclaim.exception.PolicyNumberNotFoundException;
import com.strikers.mediclaim.repository.HospitalRepository;
import com.strikers.mediclaim.repository.PolicyClaimRepository;
import com.strikers.mediclaim.repository.PolicyRepository;
import com.strikers.mediclaim.util.ClaimValidator;
import com.strikers.mediclaim.util.StringConstant;

@Service
public class PolicyClaimServiceImpl implements PolicyClaimService {
	
	private static final Logger logger = LoggerFactory.getLogger(PolicyClaimServiceImpl.class);

	@Autowired
	PolicyClaimRepository policyClaimRepository;

	@Autowired
	HospitalRepository hospitalRepository;
	
	@Autowired
	PolicyRepository policyRepository;
	
	
	@Qualifier(value="claimValidator")
	@Autowired
	ClaimValidator<PolicyClaimRequestDto> claimValidator;

	/**
	 * trackStatus is the method used to track the status of the policy claim by
	 * giving the referenceNumber
	 * 
	 * @throws PolicyClaimNotFoundException
	 */
	@Override
	public TrackResponseDto trackStatus(String referenceNumber) throws PolicyClaimNotFoundException {
		logger.info("Tracking the status");
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


	/**
	 * @author Sri Keerthna.
	 * @since 2019-12-11
	 * @param input is taken from PolicyClaimRequestDto If the policy number matches
	 *              with the claim policy number then it will allow to apply for
	 *              claim or else it will show an error message
	 * @return reference number with message is given in PolicyClaimResponseDto
	 */
	@Override
	public PolicyClaimResponseDto applyPolicyClaim(PolicyClaimRequestDto policyClaimRequestDto)
			throws PolicyNumberNotFoundException {
		PolicyClaimResponseDto policyClaimResponseDto = new PolicyClaimResponseDto();
		if(claimValidator.validate(policyClaimRequestDto)) {
			PolicyClaim policyClaim = new PolicyClaim();
			Optional<Policy> policyNumber = policyRepository.findByPolicyNumber(policyClaimRequestDto.getPolicyNumber());
			if (policyNumber.isPresent()) {
				logger.info("Got the policy number");
				String referenceNumber = "MC" + policyClaimRequestDto.getPolicyNumber();
				policyClaim.setReferenceNumber(referenceNumber);
				policyClaim.setClaimStatus(StringConstant.PENDING_STATUS);
				LocalDate createdDate = LocalDate.now();
				policyClaim.setCreatedDate(createdDate);
				BeanUtils.copyProperties(policyClaimRequestDto, policyClaim);
				Optional<Hospital> optionalHospital=hospitalRepository.findById(policyClaimRequestDto.getHospitalId());
				if(optionalHospital.isPresent())
					policyClaim.setHospital(optionalHospital.get());
				policyClaimRepository.save(policyClaim);
				policyClaimResponseDto.setMessage(StringConstant.SUCCESS);
				policyClaimResponseDto.setRefernceNumber(policyClaim.getReferenceNumber());
				return policyClaimResponseDto;
			} else {
				throw new PolicyNumberNotFoundException(StringConstant.FAILURE);
			}
		}
		return policyClaimResponseDto;
	}
}
