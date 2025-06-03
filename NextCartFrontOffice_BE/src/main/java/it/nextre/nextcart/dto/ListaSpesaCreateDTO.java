package it.nextre.nextcart.dto;

import java.time.LocalDate;
import it.nextre.nextcart.entity.ListaSpesa;

public class ListaSpesaCreateDTO {
	
	private String nomeLista;
    private LocalDate dataPrevista;
	
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
