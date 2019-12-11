package com.strikers.mediclaim.util.Impl;

import org.springframework.stereotype.Component;

import com.strikers.mediclaim.entity.PolicyClaim;
import com.strikers.mediclaim.util.ClaimValidator;

@Component("claimValidator")
public class ClaimValidatorImpl implements ClaimValidator<PolicyClaim> {

	@Override
	public Boolean validate(PolicyClaim t) {
		// TODO Auto-generated method stub
		return null;
	}

}
