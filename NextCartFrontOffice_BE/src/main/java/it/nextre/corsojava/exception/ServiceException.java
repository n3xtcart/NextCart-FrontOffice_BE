package it.nextre.corsojava.exception;

public class ServiceException extends RuntimeException{

	public ServiceException(String message, Throwable th) {
		super(message, th);
	}
	
}
