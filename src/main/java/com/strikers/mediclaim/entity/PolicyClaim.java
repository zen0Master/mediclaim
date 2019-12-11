package com.strikers.mediclaim.entity;

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
	 

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer hospitalId;
	private String hospitalName;
	
	
}
