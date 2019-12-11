package com.strikers.mediclaim.service;

import org.springframework.stereotype.Service;

import com.strikers.mediclaim.dto.PolicyClaimRequestDto;
import com.strikers.mediclaim.entity.Policy;

@Service
public class PolicyClaimServiceImpl implements PolicyClaimService{

	@Override
	public String applyPolicyClaim(PolicyClaimRequestDto policyClaimRequestDto) {
		Policy policy=new Policy();
		String policyNumber=policy.;
		if(policyNumber.contains(policyClaimRequestDto.getPolicyNumber()))
		return null;
	}

}
