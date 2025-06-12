package it.nextre.nextcart.exception;

public class AccessoNegatoException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;


	public AccessoNegatoException(String messaggio) {
    	super(messaggio);
    }

}
