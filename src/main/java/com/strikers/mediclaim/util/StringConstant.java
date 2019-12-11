package com.strikers.mediclaim.util;

/**
 * 
 * @author Sujal
 *
 */
public class StringConstant {

    private StringConstant(){ }

    public static final String ACTIVE_STATUS = "ACTIVE";
	public static final String DEACTIVE_STATUS = "DEACTIVE";
	
	public static final String PENDING_STATUS = "PENDING";
	public static final String APPROVE_STATUS = "APPROVE";
	public static final String REJECT_STATUS = "REJECT";
	public static final String PUSHBACK_STATUS = "PUSHBACK";
	
	public static final String APPROVER_ROLE = "APPROVER";
	public static final String SENIOR_APPROVER_ROLE = "SENIOR APPROVER";
	
	public static final Double POLICY_LIMIT=100000D;
	
	public static final String IN_LIMIT = "Amount within "+POLICY_LIMIT;
	public static final String EXCEED_LIMIT = "Amount exceeded "+POLICY_LIMIT;
}

