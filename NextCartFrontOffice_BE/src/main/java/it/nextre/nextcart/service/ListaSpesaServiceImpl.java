package it.nextre.nextcart.service;



import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;

import it.nextre.nextcart.dao.ListaSpesaRepository;
import it.nextre.nextcart.dto.ListaSpesaRequestDTO;
import it.nextre.nextcart.dto.ListaSpesaResponseDTO;
import it.nextre.nextcart.dto.ListaSpesaSummaryDTO;
import it.nextre.nextcart.dto.ProdottoDTO;
import it.nextre.nextcart.dto.UserListaSpesaDTO;
import it.nextre.nextcart.entity.ListaSpesa;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ListaSpesaServiceImpl implements ListaSpesaService {

    private Logger log; 
    private  ListaSpesaRepository repo;
    private ClientProd prodotti;


	@Override
	public UserListaSpesaDTO createLista(ListaSpesaRequestDTO dto) {
		
		ListaSpesa listaSpesa = dto.toEntity();
		
		repo.persist(listaSpesa);

		UserListaSpesaDTO responseDTO = new UserListaSpesaDTO();
	    responseDTO.setListeSpesa(
	        List.of(ListaSpesaSummaryDTO.fromEntity(listaSpesa))
	    );

	    return responseDTO;	
	}
	

	@Override
	public UserListaSpesaDTO getListeByUser(Long userId) {
		
		// TODO: controllo se la lista non è vuota
	    List<ListaSpesa> liste = repo.findByIdUtente(userId);
	    return UserListaSpesaDTO.fromEntity(userId, liste);
	}

	@Override
	public ListaSpesaResponseDTO getListaByIdAndUser(Long userId, Long listaId) {
		
		Optional<ListaSpesa> listaTrovata = repo.findByIdUtenteAndIdLista(listaId, userId);	
		
		// TODO: controllo se la lista è presente - non è vuota
		
	    ListaSpesa lista = listaTrovata.get();
	    
	    List<ProdottoDTO> prodottiShop = prodotti.getProdotti(); 

        return ListaSpesaResponseDTO.fromEntity(lista, prodottiShop);
	}

	@Override
	public boolean deleteLista(Long listaId) {

		// TODO: controllo se la lista è presente 
		
		return repo.deleteById(listaId);
		
	}

    

    }