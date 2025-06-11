package it.nextre.nextcart.exception;

public class ListaNotFoundException extends RuntimeException {

    private Long listaId;

    public ListaNotFoundException(Long listaId) {
    	
        this.listaId = listaId;
    }

    public Long getListaId() {
        return listaId;
    }
    
}
