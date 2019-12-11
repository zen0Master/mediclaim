package com.strikers.mediclaim.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "policy_claim")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PolicyClaim {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer policyClaimId;
	private String referenceNumber;
	private String name;
	private String policyNumber;
	private String diagnosis;
	private LocalDate admissionDate;
	private LocalDate dischargeDate;
	private Double claimAmount;
	private Integer hosipitalId;
	private String dischargeSummary;
	private String natureOfAliment;
	private String claimStatus;
	private LocalDate createdDate;

}
