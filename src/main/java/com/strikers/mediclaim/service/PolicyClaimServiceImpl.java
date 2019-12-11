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
import com.strikers.mediclaim.entity.Hospital;
import com.strikers.mediclaim.entity.Policy;
import com.strikers.mediclaim.entity.PolicyClaim;
import com.strikers.mediclaim.exception.PolicyNumberNotFoundException;
import com.strikers.mediclaim.repository.HospitalRepository;
import com.strikers.mediclaim.repository.PolicyClaimRepository;
import com.strikers.mediclaim.repository.PolicyRepository;
import com.strikers.mediclaim.util.ClaimValidator;
import com.strikers.mediclaim.util.StringConstant;

import lombok.extern.slf4j.Slf4j;

@Service
public class PolicyClaimServiceImpl implements PolicyClaimService {

	@Autowired
	PolicyClaimRepository policyClaimRepository;

	@Autowired
	PolicyRepository policyRepository;
	
	@Autowired
	HospitalRepository hospitalRepository;
	
	@Qualifier(value="claimValidator")
	@Autowired
	ClaimValidator<PolicyClaimRequestDto> claimValidator;

	private static final Logger logger = LoggerFactory.getLogger(PolicyClaimServiceImpl.class);

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
