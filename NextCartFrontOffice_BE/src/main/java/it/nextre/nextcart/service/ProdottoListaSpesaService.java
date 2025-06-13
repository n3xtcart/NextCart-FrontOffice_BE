package it.nextre.nextcart.service;

import it.nextre.nextcart.dto.ProdottoListaSpesaRequestDTO;
import it.nextre.nextcart.dto.ProdottoListaSpesaResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface ProdottoListaSpesaService {
	
	Long addProdottoToLista(Long idUtente, Long listaId, ProdottoListaSpesaRequestDTO dto);
	Long removeProdotto(Long idUtente, Long prodottoId);
	ProdottoListaSpesaResponseDTO updateProdotto(Long idUtente, Long prodottoId, ProdottoListaSpesaRequestDTO dto);
	
}


