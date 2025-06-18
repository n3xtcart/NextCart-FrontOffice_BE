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
	public Long addProdottoToLista(Long listaId, ProdottoListaSpesaRequestDTO dto) {
		
    	//Long idUtente = Long.valueOf(securityIdentity.getAttribute("userId"));
    	Long idUtente = 23L; //TODO da eliminare
		
		log.infof("Richiesta di aggiunta prodotto per l'utente con id: %d alla lista con id: %d", idUtente, listaId);
		
		 var lista = listaRepository.findById(listaId);		 
		 
		 if (lista == null) {
			 log.warnf("Lista con id %d non trovata", listaId);
			 throw new RisorsaNotFoundException("Risorsa non trovata");
		}
		 		 
		 if (!lista.getIdUtente().equals(idUtente)) {
			 log.warnf("Utente con id: %d non autorizzato ad aggiungere un prodotto alla lista con id: %d", idUtente, listaId);
		     throw new AccessoNegatoException("Accesso negato");
		 }
		 
		 boolean trovato = lista.getProdotti().stream()
				 .anyMatch(prodotto -> prodotto.getIdProdottoShop().equals(dto.getIdProdottoShop()));
		 
		 if (trovato) {
			 log.warnf("Prodotto dello shop con id: %d già presente nella lista con id: %d", dto.getIdProdottoShop(), listaId);
		     throw new ProdottoPresenteException("Prodotto gia' presente nell lista");
		}
		 
		var prodottoOptional = prodottoClient.trovaPerId(dto.getIdProdottoShop());
		    
	    if (prodottoOptional.isEmpty()) {
	    	log.warnf("Prodotto nello shop con id: %d non trovato", dto.getIdProdottoShop());
	        throw new RisorsaNotFoundException("Prodotto nello shop non trovato");
	    }
	    
	    ProdottoDTO prodottoShop = prodottoOptional.get();
	    
	    if(dto.getQuantitaProdotto().compareTo(prodottoShop.getQuantita()) > 0) {
	        log.warnf("Quantità richiesta (%d) maggiore di quella disponibile (%d) per il prodotto con id: %d",
	                 dto.getQuantitaProdotto(), prodottoShop.getQuantita(), dto.getIdProdottoShop());
	    	throw new QuantitaUnavailableException("Quantita' non disponibile");
	    }
	    
	    var prodotto = dto.toEntity(lista);
		prodottiRepository.persist(prodotto);
		lista.getProdotti().add(prodotto);
		return prodotto.id;
	}

	@Override
	@Transactional
	public ProdottoListaSpesaResponseDTO updateProdotto(Long prodottoId, ProdottoListaSpesaRequestDTO dto) {
		
    	//Long idUtente = Long.valueOf(securityIdentity.getAttribute("userId"));
    	Long idUtente = 23L; //TODO da eliminare
		
		log.infof("Inizio aggiornamento prodotto con id: %d per l'utente con id: %d", prodottoId, idUtente);
		
	    var prodotto = prodottiRepository.findById(prodottoId);
	    if (prodotto == null) {
	    	log.warnf("Prodotto con id %d non trovato.", prodottoId);
	        throw new RisorsaNotFoundException("Risorsa non trovata");
	    }
	    
	    var lista = listaRepository.findById(prodotto.getListaSpesa().id);
	    if (lista == null) {
	    	log.warnf("Lista con id {} associata al prodotto non trovata.", prodotto.getListaSpesa().id);
	        throw new RisorsaNotFoundException("Prodotto non presente nella lista.");
	    }

	    if (!lista.getIdUtente().equals(idUtente)) {
	    	log.warnf("Utente con id %d non autorizzato ad aggiornare il prodotto con id %d", idUtente, prodottoId);
	        throw new AccessoNegatoException("Accesso negato");
	    }
		
	    var prodottoOptional = prodottoClient.trovaPerId(prodotto.getIdProdottoShop());
	    
	    if (prodottoOptional.isEmpty()) {
	    	log.warnf("Prodotto nello shop con id %d non trovato.", prodotto.getIdProdottoShop());
	        throw new RisorsaNotFoundException("Prodotto nello shop non trovato");
	    }
	    
	    ProdottoDTO prodottoShop = prodottoOptional.get();
	    
	    if(dto.getQuantitaProdotto().compareTo(prodottoShop.getQuantita()) > 0) {
	    	log.warnf("Quantità richiesta (%d) superiore a quella disponibile (%d).", dto.getQuantitaProdotto(), prodottoShop.getQuantita());
	    	throw new QuantitaUnavailableException("Quantita' non disponibile");
	    }
	    
		prodotto.setNote(dto.getNoteProdotto());
		prodotto.setQuantita(dto.getQuantitaProdotto());
	    prodotto.setChecked(dto.getCheckedProdotto());
	    
	    var rispostaDTO = ProdottoListaSpesaResponseDTO.fromEntity(prodotto, prodottoShop);
		return rispostaDTO;
	}
	

	@Override
	@Transactional
	public Long removeProdotto(Long prodottoId) {
		
    	//Long idUtente = Long.valueOf(securityIdentity.getAttribute("userId"));
    	Long idUtente = 23L; //TODO da eliminare
    	
		log.infof("Inizio eliminazione prodotto con id: %d per l'utente con id: %d", prodottoId, idUtente);
		
		var prodotto = prodottiRepository.findById(prodottoId);

        if (prodotto == null) {
        	log.warnf("Prodotto con id %d non trovato.", prodottoId);
            throw new RisorsaNotFoundException("Risorsa non trovata");
        }
        
        var lista = listaRepository.findById(prodotto.getListaSpesa().id);
        
        if (lista == null) {
        	
        	log.warnf("Lista con id %d associata al prodotto non trovata.", prodotto.getListaSpesa().id);
            throw new RisorsaNotFoundException("Prodotto non presente nella lista.");
        }
        
        if (!lista.getIdUtente().equals(idUtente)) {
        	log.warnf("Utente con id %d non autorizzato a eliminare il prodotto con id %d", idUtente, prodottoId);
            throw new AccessoNegatoException("Accesso negato");
        }
        
        prodotto.delete();
        return prodotto.getListaSpesa().id;
	}
	
}
