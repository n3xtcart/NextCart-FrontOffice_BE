package it.nextre.nextcart.exception;

public class QuantitaUnavailableException extends RuntimeException{

	private static final long serialVersionUID = 1L;


	public QuantitaUnavailableException(String messaggio) {
    	super(messaggio);
    }
	
}
