package com.strikers.mediclaim.util;

import java.time.LocalDate;
import java.util.Random;

/**
 * 
 * @author Sujal
 *
 */
public class Utils {

	private Utils() {}

	
	/**
	 * getCurrentDate()
	 *
	 * @return
	 */
	public static LocalDate getCurrentDate() {
		return LocalDate.now();
	}

	private static final Random random = new Random();

	/**
	 * generateRandom()
	 *
	 * @param size
	 * @return
	 */
	public static int generateRandom(int size) {
		return random.nextInt(size);
	}
}
