package com.strikers.mediclaim.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ResponseClaimApproverDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private String message;
	private String statusCode;

}
