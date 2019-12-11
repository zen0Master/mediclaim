package com.strikers.mediclaim.service;

import com.strikers.mediclaim.dto.TrackResponseDto;
import com.strikers.mediclaim.exception.PolicyClaimNotFoundException;

public interface PolicyClaimService {

	public TrackResponseDto trackStatus(String referenceNumber) throws PolicyClaimNotFoundException;

}
