package com.strikers.mediclaim.util;

/**
 * 
 * @author Sujal
 *
 * @param <T>
 */
public interface ClaimValidator<T> {
	
	Boolean validate(T t);
}
