package com.strikers.mediclaim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strikers.mediclaim.entity.Hospital;
import com.strikers.mediclaim.exception.NoHospitalFoundException;
import com.strikers.mediclaim.repository.HospitalRepository;
import com.strikers.mediclaim.util.StringConstant;

@Service
public class HospitalServiceImpl implements HospitalService {

	@Autowired
	HospitalRepository hospitalRepository;

	/**
	 * listHospital is used to list the Hospital
	 */
	@Override
	public List<Hospital> listHospital() throws NoHospitalFoundException {
		List<Hospital> hospitalList = hospitalRepository.findAll();
		if (hospitalList.isEmpty()) {
			throw new NoHospitalFoundException(StringConstant.HOSPITAL_LIST_NOT_FOUND);
		}
		return hospitalList;
	}

}
