package com.strikers.mediclaim.util.Impl;

import org.springframework.stereotype.Component;

import com.strikers.mediclaim.dto.PolicyClaimRequestDto;
import com.strikers.mediclaim.util.ClaimValidator;
/**
 * 
 * @author Sujal
 *
 */
@Component("claimValidator")
public class ClaimValidatorImpl implements ClaimValidator<PolicyClaimRequestDto> {

	@Override
	public Boolean validate(PolicyClaimRequestDto policyClaimRequestDto) {
		Boolean flag=true;
		if(policyClaimRequestDto.getAdmissionDate()==null)
			flag=false;
		else if(policyClaimRequestDto.getHospitalId()==null)
			flag= false;
		else if(policyClaimRequestDto.getName()==null)
			flag= false;
		else if(policyClaimRequestDto.getDiagnosis()==null)
			flag= false;
		else if(policyClaimRequestDto.getNatureOfAilment()==null)
			flag= false;
		else if(policyClaimRequestDto.getPolicyNumber()==null)
			flag= false;
		else if(policyClaimRequestDto.getDischargeDate()==null)
			flag= false;
		else if(policyClaimRequestDto.getClaimAmount()==null)
			flag= false;
		return flag;
	}

}
