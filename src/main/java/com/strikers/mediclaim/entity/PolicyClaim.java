package com.strikers.mediclaim.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PolicyClaim")
@Setter
@Getter
@NoArgsConstructor
public class PolicyClaim {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer hospitalId;
	private String hospitalName;
	
	
}
