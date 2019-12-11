package com.strikers.mediclaim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.strikers.mediclaim.dto.TrackResponseDto;
import com.strikers.mediclaim.exception.PolicyClaimNotFoundException;
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

}
