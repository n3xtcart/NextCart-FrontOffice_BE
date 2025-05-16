package it.nextre.nextcart.entity;

import java.time.LocalDate;

public class ListaSpesa {

	private Long id;
	private String nome;
	private LocalDate dataPrevista;
	private Long idUtente;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	
	@Override
	public String toString() {
		return "ListaSpesa [id=" + id + ", nome=" + nome + ", dataPrevista=" + dataPrevista + ", idUtente=" + idUtente
				+ "]";
	}
	
}
