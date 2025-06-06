package it.nextre.nextcart.service;

import it.nextre.nextcart.dto.ListaSpesaSummaryDTO;
import it.nextre.nextcart.dto.ProdottoListaSpesaRequestDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface ProdottoListaSpesaService {
	
	ListaSpesaSummaryDTO addProdottoToLista(Long listaId, ProdottoListaSpesaRequestDTO dto);
	boolean removeProdotto(Long listaId, Long prodottoId);
	ProdottoListaSpesaRequestDTO updateProdotto(Long listaId, Long prodottoId, ProdottoListaSpesaRequestDTO dto);
	
}


