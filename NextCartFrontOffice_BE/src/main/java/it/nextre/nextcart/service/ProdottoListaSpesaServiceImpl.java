package it.nextre.nextcart.service;

import org.jboss.logging.Logger;
import it.nextre.nextcart.dao.ListaSpesaRepository;
import it.nextre.nextcart.dao.ProdottoListaSpesaRepository;
import it.nextre.nextcart.dto.ListaSpesaSummaryDTO;
import it.nextre.nextcart.dto.ProdottoListaSpesaRequestDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ProdottoListaSpesaServiceImpl implements ProdottoListaSpesaService{
	
	@Inject
	ProdottoListaSpesaRepository prodottiRepository;
	
	@Inject
	ListaSpesaRepository listaRepository;
    
    private Logger log; 
    
    public ProdottoListaSpesaServiceImpl(Logger log) {
    	this.log = log;
    }

	@Override
	public ListaSpesaSummaryDTO addProdottoToLista(Long listaId, ProdottoListaSpesaRequestDTO dto) {
		
		log.info("Ricerca lista con Id: " + listaId);
		var lista = listaRepository.findById(listaId);

        if (lista == null) {
            log.warn("Lista con id: " + listaId + " non trovata. ");
            throw new NotFoundException("Lista non trovata");
        }
		
        log.info("Lista con id: " + listaId + " trovata. ");
        
        
        
        
        
        
        
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

