package it.nextre.nextcart.service;

import it.nextre.nextcart.dto.ListaSpesaRequestDTO;
import it.nextre.nextcart.dto.ListaSpesaResponseDTO;
import it.nextre.nextcart.dto.UserListaSpesaDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface ListaSpesaService {
	
    ListaSpesaRequestDTO createLista(ListaSpesaRequestDTO dto);

    UserListaSpesaDTO getListeByUser(Long userId);

    ListaSpesaResponseDTO getListaByIdAndUser(Long listaId, Long userId);

    boolean deleteLista(Long listaId);


}
