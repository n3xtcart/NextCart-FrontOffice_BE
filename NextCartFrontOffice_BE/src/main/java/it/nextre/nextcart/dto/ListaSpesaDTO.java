package it.nextre.nextcart.dto;

import java.time.LocalDate;
import java.util.List;

public class ListaSpesaDTO  {
	
	private Long idLista;
	private String nomeLista;       
	private LocalDate dataPrevista; 
	private List<ProdottoListaSpesaDTO> prodotti;
	
	public void setIdLista(Long idLista) {
		this.idLista = idLista;
	}
	
	public Long getIdLista() {
		return idLista;
	}
	
	public String getNomeLista() {
		return nomeLista;
	}
	
	public void setNomeLista(String nomeLista) {
		this.nomeLista = nomeLista;
	}
	
	public LocalDate getDataPrevista() {
		return dataPrevista;
	}
	
	public void setDataPrevista(LocalDate dataPrevista) {
		this.dataPrevista = dataPrevista;
	}
	
	public List<ProdottoListaSpesaDTO> getProdotti() {
		return prodotti;
	}

	public void setProdotti(List<ProdottoListaSpesaDTO> prodotti) {
		this.prodotti = prodotti;
	}

}
