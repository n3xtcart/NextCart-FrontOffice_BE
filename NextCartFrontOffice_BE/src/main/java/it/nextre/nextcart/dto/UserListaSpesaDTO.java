package it.nextre.nextcart.dto;

import java.util.List;
import java.util.stream.Collectors;
import it.nextre.nextcart.entity.ListaSpesa;

public class UserListaSpesaDTO {
	
	private List<ListaSpesaSummaryDTO> listeSpesa;
	
    public List<ListaSpesaSummaryDTO> getListeSpesa() {
		return listeSpesa;
	}

	public void setListeSpesa(List<ListaSpesaSummaryDTO> listeSpesa) {
		this.listeSpesa = listeSpesa;
	}

    public static UserListaSpesaDTO fromEntity(Long idUtente, List<ListaSpesa> liste) {
    	
    	if (liste == null) return null;
    	
        UserListaSpesaDTO dto = new UserListaSpesaDTO();
        
        dto.setListeSpesa(
            liste.stream()
                 .map(ListaSpesaSummaryDTO::fromEntity)
                 .collect(Collectors.toList())
        );

        return dto;
    }

}
