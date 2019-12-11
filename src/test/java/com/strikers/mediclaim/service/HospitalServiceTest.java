package com.strikers.mediclaim.service;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.strikers.mediclaim.entity.Hospital;
import com.strikers.mediclaim.exception.NoHospitalFoundException;
import com.strikers.mediclaim.repository.HospitalRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class HospitalServiceTest {

	@InjectMocks
	HospitalServiceImpl hospitalServiceImpl;

	@Mock
	HospitalRepository hospitalRepository;

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
		Mockito.when(hospitalRepository.findAll()).thenReturn(hospitalList);
		List<Hospital> hospitalList = hospitalServiceImpl.listHospital();
		assertEquals(1, hospitalList.size());
	}

	@Test(expected = NoHospitalFoundException.class)
	public void testListHospitalNegative() throws NoHospitalFoundException {
		List<Hospital> hospitalLists = new ArrayList<>();
		Mockito.when(hospitalRepository.findAll()).thenReturn(hospitalLists);
		List<Hospital> hospitalList = hospitalServiceImpl.listHospital();
		assertEquals(0, hospitalList.size());
	}
}
