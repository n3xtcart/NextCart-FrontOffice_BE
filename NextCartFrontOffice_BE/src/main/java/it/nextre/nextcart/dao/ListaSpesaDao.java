package it.nextre.nextcart.dao;

import java.util.List;
//import java.util.Optional;
import it.nextre.nextcart.entity.ListaSpesa;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface ListaSpesaDao {
	
	//Optional<ListaSpesa> findById(Long id);	- da valutare
	List<ListaSpesa> findByUserId(Long idUtente);
	ListaSpesa save(ListaSpesa entity);
    void update(ListaSpesa entity);
    void delete(ListaSpesa entity);

}
