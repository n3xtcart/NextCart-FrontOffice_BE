package it.nextre.nextcart.dao;

import java.util.List;
//import java.util.Optional;
import it.nextre.nextcart.entity.ListaSpesa;

public interface ListaSpesaDao {
	
	//Optional<ListaSpesa> findById(Long id);	- da valutare
	List<ListaSpesa> findAllByUserId(Long id);
	ListaSpesa save(ListaSpesa entity);
    void update(ListaSpesa entity);
    void delete(Long id);

}
