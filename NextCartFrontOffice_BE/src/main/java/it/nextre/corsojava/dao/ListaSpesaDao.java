package it.nextre.corsojava.dao;

import it.nextre.corsojava.entity.ListaSpesa;

public interface ListaSpesaDao {
	
    void saveList(ListaSpesa entity);
    void updateList(ListaSpesa entity);
    void deleteList(Long id);

}
