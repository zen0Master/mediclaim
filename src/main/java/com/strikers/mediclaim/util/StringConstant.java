package com.strikers.mediclaim.util;

/**
 * 
 * @author Sujal
 *
 */
public class StringConstant {
	public StringConstant() {
	}

	public static final String ACTIVE_STATUS = "ACTIVE";
	public static final String PENDING_STATUS = "PENDING";
	public static final String APPROVE_STATUS = "APPROVED";
	public static final String REJECT_STATUS = "REJECTED";
	public static final String ASSIGN_STATUS = "ASSIGNED";
	public static final String DEACTIVE_STATUS = "DEACTIVE";

	public static final String APPROVER_ROLE = "APPROVER";
	public static final String SENIOR_APPROVER_ROLE = "SENIOR APPROVER";

	public static final Double POLICY_LIMIT = 100000D;

	public static final String IN_LIMIT = "Amount within " + POLICY_LIMIT;
	public static final String EXCEED_LIMIT = "Amount exceeded " + POLICY_LIMIT;

	// Raise a claim
	public static final String SUCCESS = "Claim has been submitted successfully. Please note this reference number for future tracking";
	public static final String FAILURE = "Policy number not found";
	public static final String NAME = "Name is mandatory";
	public static final String POLICY_NUMBER = "Policy number is mandatory";
	public static final String DIAGNOSIS = "diagnosis is mandatory";
	public static final String ADMISSION_DATE = "admissionDate is mandatory";
	public static final String DISCHARGE_DATE = "dischargeDate is mandatory";
	public static final String CLAIM_AMOUNT = "claimAmount is mandatory";
	public static final String DISCHARGE_SUMMARY = "dischargeSummary is mandatory";
	public static final String NATURE_OF_AILMENT = "natureOfAilment is mandatory";
	
	public static final String CLAIM_APPROVE_SUCCESS ="Claim approved successfully";
	public static final String CLAIM_REJECTED ="Claim rejected successfully";
	public static final String CLAIM_ASSIGN ="First level approval is done, pending with second level approval";

	// Tracking the status
	public static final String POLICY_CLAIM_NOT_FOUND = "No Policy Claim in this referenceNumber";
	public static final String HOSPITAL_LIST_NOT_FOUND = "No hospital available";
}
