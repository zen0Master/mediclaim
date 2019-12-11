package com.strikers.mediclaim.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.strikers.mediclaim.dto.RequestClaimApproverDto;
import com.strikers.mediclaim.dto.ResponseClaimApproverDto;
import com.strikers.mediclaim.exception.CommonException;
import com.strikers.mediclaim.service.ClaimApproverService;

/**
 * @author Vasavi
 * @since 2019-12-11
 * @description -> this class is used for to do approve claim operation.
 */
@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("")
public class ClaimApproverController {
	/**
	 * The constant log.
	 */
	private static final Logger logger = LoggerFactory.getLogger(ClaimApproverController.class);
	/**
	 * The claimApproverService.
	 */
	@Autowired
	ClaimApproverService claimApproverService;

	/**
	 * @description -> this method is used to do approve claim operation.
	 * @param ->         requestClaimApproverDto
	 * @param approverId
	 * @return -> responseClaimApproverDto
	 * @throws -> CommonException
	 */
	@PostMapping("/claimapproves/{approvedId}")
	public ResponseEntity<ResponseClaimApproverDto> approveClaim(@PathVariable("approvedId") Integer approvedId,
			@RequestBody RequestClaimApproverDto requestClaimApproverDto) throws CommonException {
		logger.info("Inside  ClaimApproverController:approveClaim");
		ResponseClaimApproverDto responseClaimApproverDto = claimApproverService.approveClaim(approvedId,
				requestClaimApproverDto);
		if (responseClaimApproverDto.getMessage().equalsIgnoreCase("SUCCESS")) {
			responseClaimApproverDto.setMessage("claim approved successfully");
			responseClaimApproverDto.setStatusCode("200");
		}else {
			responseClaimApproverDto.setMessage("claim approved failed");
			responseClaimApproverDto.setStatusCode("400");
		}
		return new ResponseEntity<>(responseClaimApproverDto, HttpStatus.OK);

	}
}
