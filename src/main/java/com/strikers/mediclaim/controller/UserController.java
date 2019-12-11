package com.strikers.mediclaim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.strikers.mediclaim.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
}
