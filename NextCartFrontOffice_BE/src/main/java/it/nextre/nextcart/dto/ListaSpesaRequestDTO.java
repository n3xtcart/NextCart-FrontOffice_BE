package it.nextre.nextcart.dto;

import java.time.LocalDate;
import it.nextre.nextcart.entity.ListaSpesa;

public class ListaSpesaRequestDTO {
	
	private Long idLista;
    private String nomeLista;
    private String dataPrevista;
	
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
	public String getDataPrevista() {
		return dataPrevista;
	}
	public void setDataPrevista(String dataPrevista) {
		this.dataPrevista = dataPrevista;
	}
	
	
    /*public void toEntity(ListaSpesa entity) {
        entity.setNome(this.nomeLista);
        entity.setDataPrevista(this.dataPrevista);
    }*/
}
