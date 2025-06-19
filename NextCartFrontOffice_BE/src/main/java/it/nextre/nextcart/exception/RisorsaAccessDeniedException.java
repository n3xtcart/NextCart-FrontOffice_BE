package it.nextre.nextcart.exception;

public class RisorsaAccessDeniedException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;


	public RisorsaAccessDeniedException(String messaggio) {
    	super(messaggio);
    }

}
