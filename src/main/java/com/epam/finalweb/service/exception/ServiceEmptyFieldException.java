package com.epam.finalweb.service.exception;

public class ServiceEmptyFieldException extends Exception{

	public ServiceEmptyFieldException() {
		super();
		
	}

	public ServiceEmptyFieldException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public ServiceEmptyFieldException(String message) {
		super(message);
		
	}

	public ServiceEmptyFieldException(Throwable cause) {
		super(cause);
		
	}

}
