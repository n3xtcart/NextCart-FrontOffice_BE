package it.nextre.nextcart.service;

import org.jboss.logging.Logger;
import it.nextre.nextcart.dao.ListaSpesaRepository;
import it.nextre.nextcart.dao.ProdottoListaSpesaRepository;
import it.nextre.nextcart.dto.ProdottoDTO;
import it.nextre.nextcart.dto.ProdottoListaSpesaRequestDTO;
import it.nextre.nextcart.dto.ProdottoListaSpesaResponseDTO;
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
	public void addProdottoToLista(Long idUtente, Long listaId, ProdottoListaSpesaRequestDTO dto) {
		
		 log.info("Richiesta di aggiunta prodotto per l'utente con id: " + idUtente + " alla lista con id: " + listaId);
		
		 var lista = listaRepository.findById(listaId);		 
		 
		 if (lista == null) {
			 log.warn("Lista con id " + listaId + " non trovata.");
			 throw new NotFoundException("Lista con id " + listaId + " non trovata."); //TODO cambiare con eccezione custom
		}
		 		 
		 if (!lista.getIdUtente().equals(idUtente)) {
		     log.warn("Utente con id: " + idUtente + " non autorizzato ad aggiungere un prodotto alla lista con id: " + listaId);
		     throw new ForbiddenException("Non sei autorizzato ad aggiungere un prodotto"); //TODO cambiare con eccezione custom
		 }
		 
		 
		 
		 
		 
		 
		 
		 
		var prodottoOptional = prodottoClient.trovaPerId(dto.getIdProdottoShop());
		    
	    if (prodottoOptional.isEmpty()) {
	        log.warn("Prodotto esterno con id :" + dto.getIdProdottoShop()  + " non trovato.");
	        throw new NotFoundException("Prodotto nello shop non trovato"); //TODO cambiare con eccezione custom
	    }
	    
	    ProdottoDTO prodottoShop = prodottoOptional.get();
	    
	    if(dto.getQuantitaProdotto().compareTo(prodottoShop.getQuantita()) > 0) {
	    	log.warn("La quantità richiesta è maggiore di quella disponibile.");
	    	throw new IllegalArgumentException("La quantità richiesta supera la disponibilità del prodotto."); //TODO cambiare con eccezione custom
	    }
	    
	    var prodotto = dto.toEntity(lista);
		prodottiRepository.persist(prodotto);
		log.info("Prodotto con id " + prodotto.id + " aggiunto correttamente per l'utente con id: " + idUtente);
	}

	@Override
	@Transactional
	public ProdottoListaSpesaResponseDTO updateProdotto(Long idUtente, Long prodottoId, ProdottoListaSpesaRequestDTO dto) {
		
		log.info("Richiesta di aggiornamento prodotto con id: " + prodottoId + " per l'utente con id: " + idUtente);
		
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
	        log.warn("Utente con id: " + idUtente + " non autorizzato ad aggiornare il prodotto con id: " + prodottoId);
	        throw new ForbiddenException("Non sei autorizzato ad aggiornare questo prodotto"); //TODO cambiare con eccezione custom
	    }
		
	    var prodottoOptional = prodottoClient.trovaPerId(dto.getIdProdottoShop());
	    
	    if (prodottoOptional.isEmpty()) {
	        log.warn("Prodotto esterno con id :" + dto.getIdProdottoShop()  + " non trovato.");
	        throw new NotFoundException("Prodotto nello shop non trovato"); //TODO cambiare con eccezione custom
	    }
	    
	    ProdottoDTO prodottoShop = prodottoOptional.get();
	    
	    if(dto.getQuantitaProdotto().compareTo(prodottoShop.getQuantita()) > 0) {
	    	log.warn("La quantità richiesta è maggiore di quella disponibile.");
	    	throw new IllegalArgumentException("La quantità richiesta supera la disponibilità del prodotto."); //TODO cambiare con eccezione custom
	    }
	    
		prodotto.setNote(dto.getNoteProdotto());
		prodotto.setQuantita(dto.getQuantitaProdotto());
	    prodotto.setChecked(dto.getCheckedProdotto());
	    
	    var rispostaDTO = ProdottoListaSpesaResponseDTO.fromEntity(prodotto, prodottoShop);
	    
	    log.info("Prodotto id: " + prodottoId + " aggiornato con successo per utente id: " + idUtente);
		return rispostaDTO;
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

