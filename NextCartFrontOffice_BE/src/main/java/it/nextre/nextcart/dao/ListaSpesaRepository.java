package it.nextre.nextcart.dao;

import java.util.List;
import java.util.Optional;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import it.nextre.nextcart.entity.ListaSpesa;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class ListaSpesaRepository implements PanacheRepository<ListaSpesa>{
	
	public List<ListaSpesa> findByIdUtente(Long idUtente) {
	    return find("idUtente", idUtente).list();
	}

	public Optional<ListaSpesa> findByIdUtenteAndIdLista(Long listaId, Long idUtente) {
	    return find("id = ?1 and idUtente = ?2", listaId, idUtente).firstResultOptional();

	}



}
