package com.strikers.mediclaim.util;

import org.springframework.stereotype.Component;

@Component
public class ApplicationConstants {
	private ApplicationConstants() {
		
	}
	public static final String INVALID_APPROVAL_DETAILS = "All details are mandatory";
	public static final String INVALID_APPROVER_ID = "Approver not found";
	public static final String INVALID_LEVEL_ONE_STATUS = "Invalid level one status";
	public static final String INVALID_LEVEL_TWO_STATUS = "Invalid level two status";
	public static final String NO_CLAIMS_FOUND = "No claims found";
}
