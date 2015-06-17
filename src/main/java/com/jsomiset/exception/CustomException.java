package com.jsomiset.exception;

import java.util.HashMap;
import java.util.Map;

public class CustomException extends Exception {
	private static final long serialVersionUID = 8484535260333474545L;
	
	private int code;
	private Map errors = new HashMap();

	public CustomException() {
	}

	public CustomException(String message) {
		super(message);
	}

	public CustomException(Throwable cause) {
		super(cause);
	}

	public CustomException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Map getErrors() {
		return errors;
	}

	public void setErrors(Map errors) {
		this.errors = errors;
	}

}
