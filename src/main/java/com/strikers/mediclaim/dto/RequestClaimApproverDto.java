package com.strikers.mediclaim.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class RequestClaimApproverDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer PolicyClaimId;
	private String status;
	

}
