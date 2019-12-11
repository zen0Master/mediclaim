package com.strikers.mediclaim.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrackResponseDto {

	private String referenceNumber;
	private String name;
	private String policyNumber;
	private String diagnosis;
	private LocalDate admissionDate;
	private LocalDate dischargeDate;
	private Double claimAmount;
	private String hospitalName;
	private String dischargeSummary;
	private String natureOfAilment;
	private String claimStatus;
	private LocalDate createdDate;

}
