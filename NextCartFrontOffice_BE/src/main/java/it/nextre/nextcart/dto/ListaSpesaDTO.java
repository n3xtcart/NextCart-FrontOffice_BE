package it.nextre.nextcart.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import it.nextre.nextcart.entity.ListaSpesa;

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
	
    public static ListaSpesaDTO fromEntity(ListaSpesa entity) {
        
    	if (entity == null) return null;

        ListaSpesaDTO dto = new ListaSpesaDTO();
        dto.setNomeLista(entity.getNome());
        dto.setDataPrevista(entity.getDataPrevista());
        
        dto.prodotti = Optional.ofNullable(entity.getProdotti())
                .orElse(new ArrayList<>())
                .stream()
                .map(ProdottoListaSpesaDTO::fromEntity)
                .collect(Collectors.toList());

        return dto;
    }
     
    public ListaSpesa toEntity() {
        ListaSpesa entity = new ListaSpesa();
        entity.setNome(this.nomeLista);
        entity.setDataPrevista(this.dataPrevista);
        
        if (this.prodotti != null) {
            entity.setProdotti(this.prodotti.stream()
                .map(ProdottoListaSpesaDTO::toEntity)
                .collect(Collectors.toList()));
        }else {
            entity.setProdotti(new ArrayList<>());
        }

        return entity;
    }

}
