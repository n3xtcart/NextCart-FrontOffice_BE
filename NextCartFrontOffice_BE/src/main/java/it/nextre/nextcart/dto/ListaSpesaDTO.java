package it.nextre.nextcart.dto;

import java.time.LocalDate;

import it.nextre.nextcart.entity.ListaSpesa;

public class ListaSpesaDTO {
	
	private Long idLista;
	private String nomeLista;       
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
	
	public static ListaSpesaDTO fromEntity(ListaSpesa entity) {
		
	    if (entity == null) return null;
	    
	    ListaSpesaDTO dto = new ListaSpesaDTO();
	    dto.setIdLista(entity.id);
	    dto.setNomeLista(entity.getNome());
	    dto.setDataPrevista(entity.getDataPrevista());
	    
	    return dto;
	}
	
    public ListaSpesa toEntity() {
        ListaSpesa entity = new ListaSpesa();
        entity.id = this.idLista;
        entity.setNome(this.nomeLista);
        entity.setDataPrevista(this.dataPrevista);
        return entity;
    }

}
