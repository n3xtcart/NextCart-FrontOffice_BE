package it.nextre.corsojava.dao;

import it.nextre.corsojava.entity.ListaSpesa;

public interface ListaSpesaDao {
	
	void findById(Long id);
    void saveList(ListaSpesa entity);
    void updateList(ListaSpesa entity);
    void deleteList(Long id);

}
