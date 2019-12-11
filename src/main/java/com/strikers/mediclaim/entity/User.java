package com.strikers.mediclaim.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "sequence",initialValue = 1001)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequence")
	private Integer userId;
	private String username;
	private String password;
	private String role;
	private String status;

}
