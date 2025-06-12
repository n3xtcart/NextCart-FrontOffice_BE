package it.nextre.nextcart.exception;

public class ProdottoPresenteException extends RuntimeException{

	private static final long serialVersionUID = 1L;


	public ProdottoPresenteException(String messaggio) {
    	super(messaggio);
    }
}
