package com.strikers.mediclaim.util;

public interface ClaimComposer<I, O> {
	
	O compose(I entity);
}
