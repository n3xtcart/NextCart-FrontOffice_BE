package it.nextre.nextcart.service;

import java.util.Optional;
import org.jboss.logging.Logger;
import it.nextre.nextcart.dao.ListaSpesaRepository;
import it.nextre.nextcart.dao.ProdottoListaSpesaRepository;
import it.nextre.nextcart.dto.ProdottoDTO;
import it.nextre.nextcart.dto.ProdottoListaSpesaRequestDTO;
import it.nextre.nextcart.entity.ProdottoListaSpesa;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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

