package it.nextre.nextcart.dao;

import java.util.Optional;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import it.nextre.nextcart.entity.ProdottoListaSpesa;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProdottoListaSpesaRepository implements PanacheRepository<ProdottoListaSpesa>{
	
	public Optional<ProdottoListaSpesa> findByIdProdottoAndIdUtente(Long prodottoId, Long idUtente) {
        return find("id = ?1 and listaSpesa.idUtente = ?2", prodottoId, idUtente).firstResultOptional();
	}
}
