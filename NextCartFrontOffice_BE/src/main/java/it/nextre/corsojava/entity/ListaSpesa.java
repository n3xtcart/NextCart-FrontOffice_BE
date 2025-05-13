package it.nextre.corsojava.entity;

import java.time.LocalDate;
import java.util.List;

public class ListaSpesa {
	
	private Long id;
	private String nome;
	private LocalDate dataPrevista;
	private Long idUtente;
	private List<ProdottoListaSpesa> prodotti;
	
	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public LocalDate getDataPrevista() {
		return dataPrevista;
	}
	
	public void setDataPrevista(LocalDate dataPrevista) {
		this.dataPrevista = dataPrevista;
	}
	
	public Long getIdUtente() {
		return idUtente;
	}
	
	public void setIdUtente(Long idUtente) {
		this.idUtente = idUtente;
	}
	
	public List<ProdottoListaSpesa> getProdotti() {
		return prodotti;
	}
	
	public void setProdotti(List<ProdottoListaSpesa> prodotti) {
		this.prodotti = prodotti;
	}	

}
