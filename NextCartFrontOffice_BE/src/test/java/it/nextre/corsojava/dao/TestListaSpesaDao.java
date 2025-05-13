package it.nextre.corsojava.dao;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import it.nextre.corsojava.entity.ListaSpesa;

public class TestListaSpesaDao {
	
	@Test
	public void testSaveLista() {
		
		ListaSpesaDao nuovaLista = new ListaSpesaDaoImpl();
		ListaSpesa listaEntity = new ListaSpesa();	
		listaEntity.setIdUtente(1L);
		listaEntity.setNome("Lista di prova");
		listaEntity.setDataPrevista(LocalDate.parse("2025-12-12"));
		//nuovaLista.saveList(listaEntity);
		
	}
	

}
