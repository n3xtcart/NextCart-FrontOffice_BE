package it.nextre.nextcart.service;

import java.util.Optional;
import org.jboss.logging.Logger;
import it.nextre.nextcart.dao.ListaSpesaRepository;
import it.nextre.nextcart.dao.ProdottoListaSpesaRepository;
import it.nextre.nextcart.dto.ProdottoDTO;
import it.nextre.nextcart.dto.ProdottoListaSpesaRequestDTO;
import it.nextre.nextcart.dto.ProdottoListaSpesaResponseDTO;
import it.nextre.nextcart.entity.ProdottoListaSpesa;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ProdottoListaSpesaServiceImpl implements ProdottoListaSpesaService{
	
	@Inject
	ProdottoListaSpesaRepository prodottiRepository;
	
	@Inject
	ListaSpesaRepository listaRepository;
	
	@Inject
	ClientProd prodottoClient;
	 
    private Logger log; 
    
    public ProdottoListaSpesaServiceImpl(Logger log) {
    	this.log = log;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	@Override
	@Transactional
	public boolean addProdottoToLista(Long listaId, ProdottoListaSpesaRequestDTO dto) {
		
		//TODO mancano i controlli
		log.info("Ricerca lista con Id: " + listaId);
		var lista = listaRepository.findById(listaId);

        if (lista == null) {
            log.warn("Lista con id: " + listaId + " non trovata. ");
            throw new NotFoundException("Lista non trovata");
        }
		
        log.info("Lista con id: " + listaId + " trovata. "); 
        ProdottoListaSpesa prodottoEntity = dto.toEntity(lista);
        
        Optional<ProdottoDTO> optionalProdotto = prodottoClient.trovaPerId(prodottoEntity.getIdProdottoShop());

        if (optionalProdotto.isEmpty()) {
            log.warn("Prodotto con id: " + prodottoEntity.getIdProdottoShop() + " non trovato nel sistema shop.");
            throw new NotFoundException("Prodotto non presente in shop con id " + prodottoEntity.getIdProdottoShop());
        }
        
        prodottoEntity.persist();
        log.info("Prodotto aggiunto alla lista con successo.");
        return true;
	}

	
	
	
	
	
	
	
	
	
	
	
	@Override
	public ProdottoListaSpesaResponseDTO updateProdotto(Long prodottoId, ProdottoListaSpesaRequestDTO dto) {
		
		
		
		
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Override
	@Transactional
	public void removeProdotto(Long idUtente, Long prodottoId) {
		
		log.info("Richiesta di eliminazione prodotto con id: " + prodottoId + " per l'utente con id: "  + idUtente );
		
		var prodotto = prodottiRepository.findById(prodottoId);

        if (prodotto == null) {
        	log.warn("Prodotto con id " + prodottoId + " non trovato");
            throw new NotFoundException("Prodotto con id " + prodottoId + " non trovato."); //TODO cambiare con eccezione custom
        }
        
        var lista = listaRepository.findById(prodotto.getListaSpesa().id);
        
        if (lista == null) {
        	
        	log.warn("Lista con id " + prodotto.getListaSpesa().id + " non trovata.");
            throw new NotFoundException("Lista associata al prodotto non trovata."); //TODO cambiare con eccezione custom
        }
        
        if (!lista.getIdUtente().equals(idUtente)) {
        	log.warn("Utente con id: " + idUtente + "non autorizzato ad eliminare il prodotto con id: " + prodottoId);
            throw new ForbiddenException("Non sei autorizzato a eliminare questo prodotto"); //TODO cambiare con eccezione custom
        }
        
        prodotto.delete();
		log.info("Richiesta di eliminazione prodotto con id: " + prodottoId + " per l'utente con id: "  + idUtente + "conclusa correttamente.");
	}
	
}

