package it.nextre.nextcart.service;

import it.nextre.nextcart.dto.ProdottoListaSpesaDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface ProdottoListaSpesaService {

	    ProdottoListaSpesaDTO addProdottoToLista(Long listaId, String email, ProdottoListaSpesaDTO dto);

	    void removeProdotto(Long listaId, Long prodottoId, String email);

	    ProdottoListaSpesaDTO updateProdotto(Long listaId, Long prodottoId, String email, ProdottoListaSpesaDTO dto);
	}


