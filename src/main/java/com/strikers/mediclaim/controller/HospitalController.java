package com.strikers.mediclaim.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.strikers.mediclaim.entity.Hospital;
import com.strikers.mediclaim.exception.NoHospitalFoundException;
import com.strikers.mediclaim.service.HospitalService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/hospitals")
@Slf4j
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class HospitalController {

	@Autowired
	HospitalService hospitalService;

	/**
	 * listHospital is used to list the Hospital
	 * 
	 * @return
	 * @throws NoHospitalFoundException
	 */
	@GetMapping()
	public ResponseEntity<List<Hospital>> listHospital() throws NoHospitalFoundException {
		log.info("Listing all the Hospitals");
		List<Hospital> hospitalList = hospitalService.listHospital();
		if (hospitalList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(hospitalList, HttpStatus.OK);
		}
	}

}
