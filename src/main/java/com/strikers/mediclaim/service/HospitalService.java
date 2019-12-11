package com.strikers.mediclaim.service;

import java.util.List;

import com.strikers.mediclaim.entity.Hospital;
import com.strikers.mediclaim.exception.NoHospitalFoundException;

public interface HospitalService {

	List<Hospital> listHospital() throws NoHospitalFoundException;

}
