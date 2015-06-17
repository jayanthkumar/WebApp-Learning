package com.jsomiset.exception;

import org.junit.Test;

public class CustomExceptionTest {
	@Test(expected=CustomException.class)
	public void customExceptionTest() throws CustomException{
		testException(null);
	}
	
	public static void testException(String string) throws CustomException
    {
	      if(string == null)
		    throw new CustomException("The String value is null");
    }
}
