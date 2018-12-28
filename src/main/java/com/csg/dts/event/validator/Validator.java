package com.csg.dts.event.validator;

import com.csg.dts.execption.ApplicationException;

@FunctionalInterface
public interface Validator<T> {

	boolean isValid(T t);

	default void validate(T t) throws ApplicationException {
		boolean valid = isValid(t);
		if(!valid) {
			throw new ApplicationException("Invalid Input");
		}
	}

}
