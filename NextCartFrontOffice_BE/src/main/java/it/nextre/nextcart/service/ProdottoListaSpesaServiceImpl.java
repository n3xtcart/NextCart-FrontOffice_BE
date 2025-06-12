package it.nextre.nextcart.service;

import org.jboss.logging.Logger;
import it.nextre.nextcart.client.ClientProd;
import it.nextre.nextcart.dao.ListaSpesaRepository;
import it.nextre.nextcart.dao.ProdottoListaSpesaRepository;
import it.nextre.nextcart.dto.ProdottoDTO;
import it.nextre.nextcart.dto.ProdottoListaSpesaRequestDTO;
import it.nextre.nextcart.dto.ProdottoListaSpesaResponseDTO;
import it.nextre.nextcart.exception.AccessoNegatoException;
import it.nextre.nextcart.exception.ProdottoPresenteException;
import it.nextre.nextcart.exception.QuantitaUnavailableException;
import it.nextre.nextcart.exception.RisorsaNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

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
			 throw new RisorsaNotFoundException("Lista non trovata");
		}
		 		 
		 if (!lista.getIdUtente().equals(idUtente)) {
		     log.warn("Utente con id: " + idUtente + " non autorizzato ad aggiungere un prodotto alla lista con id: " + listaId);
		     throw new AccessoNegatoException("Accesso negato");
		 }
		 
		 boolean trovato = lista.getProdotti().stream()
				    .anyMatch(prodotto -> prodotto.getIdProdottoShop() == dto.getIdProdottoShop());
		 
		 if (trovato) {
			 log.warn("Prodotto dello shop con id: " + dto.getIdProdottoShop() + " e' gia' presente nella lista con id: " + listaId );
		     throw new ProdottoPresenteException("Prodotto gia' presente nell lista");
		}
		 
		var prodottoOptional = prodottoClient.trovaPerId(dto.getIdProdottoShop());
		    
	    if (prodottoOptional.isEmpty()) {
	        log.warn("Prodotto nello shop con id :" + dto.getIdProdottoShop()  + " non trovato.");
	        throw new RisorsaNotFoundException("Prodotto nello shop non trovato");
	    }
	    
	    ProdottoDTO prodottoShop = prodottoOptional.get();
	    
	    if(dto.getQuantitaProdotto().compareTo(prodottoShop.getQuantita()) > 1) {
	    	log.warn("La quantità richiesta è maggiore di quella disponibile.");
	    	throw new QuantitaUnavailableException("Quantita' non disponibile");
	    }
	    
	    var prodotto = dto.toEntity(lista);
		prodottiRepository.persist(prodotto);
		log.info("Prodotto con id " + prodotto.id + " aggiunto correttamente alla lista con id: "+ listaId);
	}

	@Override
	@Transactional
	public ProdottoListaSpesaResponseDTO updateProdotto(Long idUtente, Long prodottoId, ProdottoListaSpesaRequestDTO dto) {
		
		log.info("Richiesta di aggiornamento prodotto con id: " + prodottoId + " per l'utente con id: " + idUtente);
		
	    var prodotto = prodottiRepository.findById(prodottoId);
	    if (prodotto == null) {
	        log.warn("Prodotto con id " + prodottoId + " non trovato");
	        throw new RisorsaNotFoundException("Prodotto non trovato");
	    }

	    var lista = listaRepository.findById(prodotto.getListaSpesa().id);
	    if (lista == null) {
	        log.warn("Lista con id " + prodotto.getListaSpesa().id + " non trovata.");
	        throw new RisorsaNotFoundException("Prodotto non presente nella lista.");
	    }

	    if (!lista.getIdUtente().equals(idUtente)) {
	        log.warn("Utente con id: " + idUtente + " non autorizzato ad aggiornare il prodotto con id: " + prodottoId);
	        throw new AccessoNegatoException("Accesso negato");
	    }
		
	    var prodottoOptional = prodottoClient.trovaPerId(dto.getIdProdottoShop());
	    
	    if (prodottoOptional.isEmpty()) {
	        log.warn("Prodotto nello shop con id :" + dto.getIdProdottoShop()  + " non trovato.");
	        throw new RisorsaNotFoundException("Prodotto nello shop non trovato");
	    }
	    
	    ProdottoDTO prodottoShop = prodottoOptional.get();
	    
	    if(dto.getQuantitaProdotto().compareTo(prodottoShop.getQuantita()) > 1) {
	    	log.warn("La quantita' richiesta e' maggiore di quella disponibile.");
	    	throw new QuantitaUnavailableException("Quantita' non disponibile");
	    }
	    
		prodotto.setNote(dto.getNoteProdotto());
		prodotto.setQuantita(dto.getQuantitaProdotto());
	    prodotto.setChecked(dto.getCheckedProdotto());
	    
	    var rispostaDTO = ProdottoListaSpesaResponseDTO.fromEntity(prodotto, prodottoShop);
	    
	    log.info("Prodotto id: " + prodottoId + " nella lista con id: " + prodotto.getListaSpesa().id + " aggiornato con successo");
		return rispostaDTO;
	}
	

	@Override
	@Transactional
	public void removeProdotto(Long idUtente, Long prodottoId) {
		
		log.info("Richiesta di eliminazione prodotto con id: " + prodottoId + " per l'utente con id: "  + idUtente );
		
		var prodotto = prodottiRepository.findById(prodottoId);

        if (prodotto == null) {
        	log.warn("Prodotto con id " + prodottoId + " non trovato");
            throw new RisorsaNotFoundException("Prodotto non trovato");
        }
        
        var lista = listaRepository.findById(prodotto.getListaSpesa().id);
        
        if (lista == null) {
        	
        	log.warn("Lista con id " + prodotto.getListaSpesa().id + " non trovata.");
            throw new RisorsaNotFoundException("Prodotto non presente nella lista.");
        }
        
        if (!lista.getIdUtente().equals(idUtente)) {
        	log.warn("Utente con id: " + idUtente + "non autorizzato ad eliminare il prodotto con id: " + prodottoId);
            throw new AccessoNegatoException("Accesso negato");
        }
        
        prodotto.delete();
		log.info("Richiesta di eliminazione prodotto con id: " + prodottoId + "dalla lista con id: " + prodotto.getListaSpesa().id +" conclusa correttamente.");
	}
	
}
