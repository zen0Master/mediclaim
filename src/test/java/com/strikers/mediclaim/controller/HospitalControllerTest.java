package com.strikers.mediclaim.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.strikers.mediclaim.entity.Hospital;
import com.strikers.mediclaim.exception.NoHospitalFoundException;
import com.strikers.mediclaim.service.HospitalService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class HospitalControllerTest {

	@InjectMocks
	HospitalController hospitalController;

	@Mock
	HospitalService hospitalService;

	static Hospital hospital = new Hospital();
	static List<Hospital> hospitalList = new ArrayList<>();

	@Before
	public void setUp() {
		hospital.setHospitalId(1);
		hospital.setHospitalName("Aravind");
		hospital.setAddress("Pondicherry");
		hospitalList.add(hospital);
	}
	
	@Test
	public void testListHospitalPositive() throws NoHospitalFoundException {
		Mockito.when(hospitalService.listHospital()).thenReturn(hospitalList);
		Integer result = hospitalController.listHospital().getStatusCodeValue();
		assertEquals(200, result);
	}

	@Test
	public void testListHospitalNegative() throws NoHospitalFoundException {
		List<Hospital> hospitalLists = new ArrayList<>();
		Mockito.when(hospitalService.listHospital()).thenReturn(hospitalLists);
		Integer result = hospitalController.listHospital().getStatusCodeValue();
		assertEquals(204, result);
	}

}
