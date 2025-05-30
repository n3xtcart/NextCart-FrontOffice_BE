package it.nextre.nextcart.dto;

import java.time.LocalDate;
import java.util.List;

public class ListaSpesaDetailDTO {
	
	private Long idLista;
	private String nomeLista;       
	private LocalDate dataPrevista; 
	private List<ProdottoListaSpesaResponseDTO> prodotti;
	
	public Long getIdLista() {
		return idLista;
	}
	public void setIdLista(Long idLista) {
		this.idLista = idLista;
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
	public List<ProdottoListaSpesaResponseDTO> getProdotti() {
		return prodotti;
	}
	public void setProdotti(List<ProdottoListaSpesaResponseDTO> prodotti) {
		this.prodotti = prodotti;
	}
}
