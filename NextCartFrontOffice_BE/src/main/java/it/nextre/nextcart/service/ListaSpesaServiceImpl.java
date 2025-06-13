package it.nextre.nextcart.service;

import java.util.List;
import java.util.Optional;
import org.jboss.logging.Logger;
import it.nextre.nextcart.client.ClientProd;
import it.nextre.nextcart.dao.ListaSpesaRepository;
import it.nextre.nextcart.dto.ListaSpesaRequestDTO;
import it.nextre.nextcart.dto.ListaSpesaResponseDTO;
import it.nextre.nextcart.dto.ListaSpesaSummaryDTO;
import it.nextre.nextcart.dto.ProdottoDTO;
import it.nextre.nextcart.dto.UserListaSpesaDTO;
import it.nextre.nextcart.entity.ListaSpesa;
import it.nextre.nextcart.exception.AccessoNegatoException;
import it.nextre.nextcart.exception.RisorsaNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.SecurityContext;

@ApplicationScoped
public class ListaSpesaServiceImpl implements ListaSpesaService {

    @Inject
    Logger log;

    @Inject
    ListaSpesaRepository repo;

    @Inject
    ClientProd prodotti;
    
    @Inject
    SecurityContext securityContext;

	@Override
	@Transactional
	public UserListaSpesaDTO createLista(ListaSpesaRequestDTO dto) {
		
		//Principal userPrincipal = securityContext.getUserPrincipal();
		
		//Long idUtente = Long.valueOf(userPrincipal.getName());
		
		Long idUtente = 23L;
		
		ListaSpesa listaSpesa = dto.toEntity();
		
		listaSpesa.setIdUtente(idUtente);
		
		repo.persist(listaSpesa);

		UserListaSpesaDTO responseDTO = new UserListaSpesaDTO();
		
	    responseDTO.setListeSpesa(
	        List.of(ListaSpesaSummaryDTO.fromEntity(listaSpesa))
	    );

	    return responseDTO;	
	}
	

	@Override
	@Transactional
	public UserListaSpesaDTO getListeByUser() {
		
        Long idUtente = 23L; 

	    List<ListaSpesa> liste = repo.findByIdUtente(idUtente);
	    
        if (liste.isEmpty()) {
            throw new RisorsaNotFoundException("Risorsa non trovata");
        }
	    
	    return UserListaSpesaDTO.fromEntity(idUtente, liste);
	}
	

	@Override
	@Transactional
	public ListaSpesaResponseDTO getListaByIdAndUser(Long listaId) {
		
        Long idUtente = 23L; 

        Optional<ListaSpesa> listaTrovata = repo.findByIdUtenteAndIdLista(listaId, idUtente);
        
        if (listaTrovata.isEmpty()) {
            throw new RisorsaNotFoundException("Risorsa non trovata");
        }
        
        ListaSpesa lista = listaTrovata.get();
        
        List<ProdottoDTO> prodottiShop = prodotti.trovaTutti();
        
        return ListaSpesaResponseDTO.fromEntity(lista, prodottiShop);
    }


	@Override
	@Transactional
	public boolean deleteLista(Long idUtente, Long listaId) {
		
        ListaSpesa trovata = repo.findById(listaId);
        
        if (trovata == null) {
        	log.warn("Lista con id " + listaId + " non trovata.");
            throw new RisorsaNotFoundException("Risorsa non trovata");
        }
        
        if (!trovata.getIdUtente().equals(idUtente)) {
        	log.warn("Utente con id: " + idUtente + "non autorizzato ad eliminare la lista con id: " + listaId);
            throw new AccessoNegatoException("Accesso negato");
        }
 
		return repo.deleteById(listaId);
		
	}

   }