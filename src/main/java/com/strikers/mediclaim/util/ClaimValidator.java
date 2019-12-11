package com.strikers.mediclaim.util;

public interface ClaimValidator<T> {
	
	Boolean validate(T t);
}
