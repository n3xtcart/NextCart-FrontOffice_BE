package it.nextre.nextcart.dto;

import java.time.LocalDate;

import it.nextre.nextcart.entity.ListaSpesa;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ListaSpesaRequestDTO {
	
	private Long idLista;
	
	@Size(max = 100)
	@NotNull
	@Pattern(regexp = "^[A-Za-zÀ-ÿ0-9_ ]+$")
    private String nomeLista;
	
	@NotNull
	@FutureOrPresent
    private LocalDate dataPrevista;
	
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
	
	
	
    public ListaSpesa toEntity() {
    	ListaSpesa entity = new ListaSpesa();
        entity.setNome(this.nomeLista);
        entity.setDataPrevista(this.dataPrevista);
       
        return entity;
    }
}
