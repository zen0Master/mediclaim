package com.strikers.mediclaim.util;

/**
 * 
 * @author Sujal
 *
 * @param <I>
 * @param <O>
 */
public interface ClaimComposer<I, O> {
	
	O compose(I entity);
}
