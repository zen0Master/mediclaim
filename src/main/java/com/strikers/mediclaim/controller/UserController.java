package com.strikers.mediclaim.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.strikers.mediclaim.entity.PolicyClaim;
import com.strikers.mediclaim.entity.User;
import com.strikers.mediclaim.service.UserService;


@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;
	 
	private static final Logger Logger = LoggerFactory.getLogger(UserController.class);

	
	/**
	 * 
	 * @param mobileNumber
	 * @param password
	 * @return
	 */
	@PostMapping("/user/login")
	public ResponseEntity<List<PolicyClaim>> userLogin(@RequestBody User user) {
		Logger.info("loginUser is used to verify the user");
		if(user!=null) {
			List<PolicyClaim> policyClaims = userService.userLogin(user);
			if (policyClaims!=null) {
				return new ResponseEntity<>(policyClaims, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
