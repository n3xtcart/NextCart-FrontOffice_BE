package it.nextre.corsojava.dto;

import java.util.List;

public class UserListaSpesaDTO  {
	
	private Long idUtente;         
    private List<ListaSpesaDTO> listeSpesa;
	
	public Long getIdUtente() {
		return idUtente;
	}

	public List<ListaSpesaDTO> getListeSpesa() {
		return listeSpesa;
	}
	
	public void setListeSpesa(List<ListaSpesaDTO> listeSpesa) {
		this.listeSpesa = listeSpesa;
	}

}
