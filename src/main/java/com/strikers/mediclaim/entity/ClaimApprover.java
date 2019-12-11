package com.strikers.mediclaim.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "approver")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClaimApprover {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer claim_approver_id;
	
	@OneToOne
	@JoinColumn(name="policyClaimId")
	private PolicyClaim policyClaim;
	
	@OneToOne
	@JoinColumn(name = "approvedId")
	private User approvedId;
	
	private String status;

}
