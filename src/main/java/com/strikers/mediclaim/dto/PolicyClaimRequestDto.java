package com.strikers.mediclaim.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PolicyClaimRequestDto {

	private String name;
	private String policyNumber;
	private String diagnosis;
	private LocalDate admissionDate;
	private LocalDate dischargeDate;
	private Double claimAmount;
	private Integer hospitalId;
	private String dischargeSummary;
	private String natureOfAilment;
}
