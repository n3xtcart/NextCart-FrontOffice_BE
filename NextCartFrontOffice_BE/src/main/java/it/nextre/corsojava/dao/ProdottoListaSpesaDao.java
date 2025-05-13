package it.nextre.corsojava.dao;

import it.nextre.corsojava.entity.ListaSpesa;

public interface ProdottoListaSpesaDao {
	
    void saveProduct(ListaSpesa entity);
    void updateProduct(ListaSpesa entity);
    void deleteProduct(Long id);

}
