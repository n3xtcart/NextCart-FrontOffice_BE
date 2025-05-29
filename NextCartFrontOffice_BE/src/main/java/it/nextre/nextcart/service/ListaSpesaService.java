package it.nextre.nextcart.service;

import java.util.List;

import it.nextre.nextcart.dto.ListaSpesaDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface ListaSpesaService {
	
    ListaSpesaDTO createLista(String email, ListaSpesaDTO dto);

    List<ListaSpesaDTO> getListeByUser(Long userId);

    ListaSpesaDTO getListaByIdAndUser(Long listaId, Long userId);

    void deleteLista(String email, Long userId);

	List<ListaSpesaDTO> findByUserEmail(String email);

}
