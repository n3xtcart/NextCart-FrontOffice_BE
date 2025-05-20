package it.nextre.nextcart.entity;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "liste_spesa")
public class ListaSpesa {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", length = 100, nullable = false)
	private String nome;
	
	@Column(name = "data_prevista", nullable = false)
	private LocalDate dataPrevista;
	
	@Column(name = "id_utente", nullable = false)
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
