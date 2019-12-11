package com.strikers.mediclaim.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	 
	@OneToOne
	@JoinColumn(name="hospitalId")
	private Hospital hospital;
	private String hospitalName;
	private Double claimAmount;
	
	private String name;
	private String referenceNumber;
	private String policyNumber;
	private String diagnosis;
	private LocalDate admissionDate;
	private LocalDate dischargeDate;
	private String dischargeSummary;
	private String natureOfAilment;
	private String claimStatus;
	private LocalDate createdDate;

	@Transient
	private String remarks;
}
