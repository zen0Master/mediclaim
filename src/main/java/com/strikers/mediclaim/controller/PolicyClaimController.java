package com.strikers.mediclaim.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.strikers.mediclaim.dto.PolicyClaimRequestDto;
import com.strikers.mediclaim.dto.PolicyClaimResponseDto;
import com.strikers.mediclaim.dto.TrackResponseDto;
import com.strikers.mediclaim.exception.PolicyClaimNotFoundException;
import com.strikers.mediclaim.exception.PolicyNumberNotFoundException;
import com.strikers.mediclaim.service.PolicyClaimService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/policyclaims")
@Slf4j
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class PolicyClaimController {

	@Autowired
	PolicyClaimService policyClaimService;

	/**
	 * trackStatus is used to track the status of the policy claim by giving the
	 * unique referenceNumber
	 * 
	 * @param referenceNumber
	 * @return
	 * @throws PolicyClaimNotFoundException
	 */
	@GetMapping(value = "/{referenceNumber}")
	public ResponseEntity<TrackResponseDto> trackStatus(@PathVariable String referenceNumber)
			throws PolicyClaimNotFoundException {
		log.info("Tracking the status");
		TrackResponseDto trackResponseDto = policyClaimService.trackStatus(referenceNumber);
		if (trackResponseDto != null) {
			return new ResponseEntity<>(trackResponseDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	private static final Logger logger = LoggerFactory.getLogger(PolicyClaimController.class);

	/**
	 * @author Sri Keerthna.
	 * @since 2019-12-11
	 * @param input is taken from PolicyClaimRequestDto
	 * @return if value is present in PolicyClaimResponseDto then it will return
	 *         reference number and message or else it will show error message
	 */
	@PostMapping
	public ResponseEntity<PolicyClaimResponseDto> applyPolicyClaim(
			@RequestBody PolicyClaimRequestDto policyClaimRequestDto) throws PolicyNumberNotFoundException {
		PolicyClaimResponseDto policyClaimResponseDto = policyClaimService.applyPolicyClaim(policyClaimRequestDto);
		if (policyClaimResponseDto != null) {
			logger.info("Got the response from service layer");
			return new ResponseEntity<>(policyClaimResponseDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
