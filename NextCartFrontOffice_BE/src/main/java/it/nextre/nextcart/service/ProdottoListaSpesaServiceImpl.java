package it.nextre.nextcart.service;


import org.jboss.logging.Logger;

import it.nextre.nextcart.dto.ProdottoListaSpesaRequestDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProdottoListaSpesaServiceImpl implements ProdottoListaSpesaService{
	
    private Logger log; 
    
    public ProdottoListaSpesaServiceImpl(Logger log) {
    	this.log = log;
    }

	@Override
	public ProdottoListaSpesaRequestDTO addProdottoToLista(Long listaId, ProdottoListaSpesaRequestDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeProdotto(Long listaId, Long prodottoId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ProdottoListaSpesaRequestDTO updateProdotto(Long listaId, Long prodottoId,
			ProdottoListaSpesaRequestDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}
    
}

