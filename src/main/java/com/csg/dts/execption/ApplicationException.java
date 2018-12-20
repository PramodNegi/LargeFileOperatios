package com.csg.dts.execption;

public class ApplicationException extends Exception {
	
	private static final long serialVersionUID = -8862503653484682152L;

	public ApplicationException(String cause) {
		super(cause);
	}
	
	public ApplicationException(Exception exception) {
		super(exception);
	}

	public ApplicationException(String message, Exception exception) {
		super(message, exception);
	}
}
