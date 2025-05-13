package it.nextre.corsojava.service;

import it.nextre.corsojava.entity.ListaSpesa;
import it.nextre.corsojava.entity.ProdottoListaSpesa;

public interface ListaSpesaService {
	
	public void createProduct(ProdottoListaSpesa item);
	public void updateProduct(ProdottoListaSpesa entity);
	public void deleteProduct(Long id);
	
	public void createList(ListaSpesa item);
	public void updateList(ListaSpesa entity);
	public void deleteList(Long id);

}
