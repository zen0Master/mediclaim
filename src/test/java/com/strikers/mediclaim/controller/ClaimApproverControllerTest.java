package com.strikers.mediclaim.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.strikers.mediclaim.dto.RequestClaimApproverDto;
import com.strikers.mediclaim.dto.ResponseClaimApproverDto;
import com.strikers.mediclaim.service.ClaimApproverServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class ClaimApproverControllerTest {
	private static final Logger logger = LoggerFactory.getLogger(ClaimApproverControllerTest.class);
	@InjectMocks
	ClaimApproverController claimApproverController;
	@Mock
	ClaimApproverServiceImpl claimApproverService;
	RequestClaimApproverDto requestClaimApproverDto = new RequestClaimApproverDto();
	ResponseClaimApproverDto responseClaimApproverDto = new ResponseClaimApproverDto();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		requestClaimApproverDto.setPolicyClaimId(1);
		requestClaimApproverDto.setStatus("approved");
		responseClaimApproverDto.setMessage("claim approved failed");
		responseClaimApproverDto.setMessage("200");
	}

	@Test
	public void testApproveClaimNegative()  {
		logger.info("Inside the approveClaimTest method");

		when(claimApproverService.approveClaim(1002, requestClaimApproverDto)).thenReturn(responseClaimApproverDto);
		ResponseEntity<ResponseClaimApproverDto> result = claimApproverController.approveClaim(1002,
				requestClaimApproverDto);
		assertEquals("claim approved failed", result.getBody().getMessage());

	}
}
