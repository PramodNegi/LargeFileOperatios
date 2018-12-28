package com.csg.dts.event.validator;


import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.csg.dts.execption.ApplicationException;

@RunWith(MockitoJUnitRunner.class)
public class ValidatorTest {
	
	@Test
	public void testValidFile() throws ApplicationException {
		final Validator<String> filePathValidator = Mockito.spy(new FilePathValidator());
		Mockito.when(filePathValidator.isValid(Mockito.anyString())).thenReturn(true);
		filePathValidator.validate(Mockito.anyString());
	}
	
	@Test(expected=ApplicationException.class)
	public void testInvalidFile() throws ApplicationException {
		final Validator<String> filePathValidator = Mockito.spy(new FilePathValidator());
		Mockito.when(filePathValidator.isValid(Mockito.anyString())).thenReturn(false);
		try {
			filePathValidator.validate(Mockito.anyString());
		}catch(ApplicationException e) {
			assertTrue("Exception mistmatch", e.getMessage().contains("Invalid Input"));
			throw e;
		}
	}

}
