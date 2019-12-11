package com.strikers.mediclaim.service;


import com.strikers.mediclaim.dto.RequestClaimApproverDto;
import com.strikers.mediclaim.dto.ResponseClaimApproverDto;
import com.strikers.mediclaim.exception.CommonException;

/**
 * @author Vasavi
 * @since 2019-12-11
 * 
 *
 */
public interface ClaimApproverService {
	/**
	 * @Description ->this method is used for approve the claim
	 * @param  ->requestClaimApproverDto
	 * @return ->responseClaimApproverDto
	 * @throws -> CommandAcceptanceException
	 */
	public ResponseClaimApproverDto approveClaim(Integer approvedId, RequestClaimApproverDto requestClaimApproverDto) throws CommonException;

}
