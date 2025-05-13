package it.nextre.corsojava.service;

import it.nextre.corsojava.entity.ListaSpesa;
import it.nextre.corsojava.entity.ProdottoListaSpesa;

public interface ListaSpesaService {
	
	public void createProduct(ProdottoListaSpesa item);
	public void modifyProduct(ProdottoListaSpesa entity);
	public void removeProduct(Long id);
	
	public void createList(ListaSpesa item);
	public void modifyList(ListaSpesa entity);
	public void removeList(Long id);

}
