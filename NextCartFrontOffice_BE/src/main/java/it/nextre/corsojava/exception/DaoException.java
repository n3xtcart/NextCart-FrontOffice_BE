package it.nextre.corsojava.exception;

public class DaoException extends RuntimeException{
	
	public DaoException(String message, Throwable th) {
		super(message, th);
	}

}
