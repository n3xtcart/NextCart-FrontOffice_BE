package it.nextre.nextcart.service;

import it.nextre.nextcart.dto.ListaSpesaRequestDTO;
import it.nextre.nextcart.dto.ListaSpesaResponseDTO;
import it.nextre.nextcart.dto.UserListaSpesaDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface ListaSpesaService {
	
	UserListaSpesaDTO createLista(ListaSpesaRequestDTO dto);

    UserListaSpesaDTO getListeByUser(Long userId);

    ListaSpesaResponseDTO getListaByIdAndUser(Long userId, Long listaId );

    boolean deleteLista(Long listaId);


}
