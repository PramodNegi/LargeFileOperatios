package com.csg.dts.execption;

public class ApplicationRuntimeException extends RuntimeException {
	
	private static final long serialVersionUID = 5381437502234540158L;

	public ApplicationRuntimeException(String cause) {
		super(cause);
	}
	
	public ApplicationRuntimeException(Exception exception) {
		super(exception);
	}

	public ApplicationRuntimeException(String message, Exception exception) {
		super(message, exception);
	}
}
