package it.nextre.nextcart.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import it.nextre.nextcart.entity.ListaSpesa;

public class TestListaSpesaDao {
	
	@Test
	public void testFindAllByUserId() {
		
		ListaSpesaDao listaSpesaDao = new ListaSpesaDaoImpl();
		Long userId = 1L;
		List<ListaSpesa> risultato = listaSpesaDao.findAllByUserId(userId);
		
		assertNotNull(risultato);
		assertEquals(risultato.get(0).getId(), 1);
		assertEquals(risultato.get(0).getNome(), "Lista di prova");
		assertEquals(risultato.get(0).getDataPrevista(), LocalDate.of(2025, 12, 12));
		assertEquals(risultato.get(0).getIdUtente(), 1);
	}
	
	@Test
	public void testFindAllByNotUserId() {
		ListaSpesaDao listaSpesaDao = new ListaSpesaDaoImpl();
		Long userId = 9999L;
		
		List<ListaSpesa> risultato = listaSpesaDao.findAllByUserId(userId);
		assertNotNull(risultato);
		assertTrue(risultato.isEmpty());	
	}
	
	@Test
	public void testFindAllByIdNull() {
		ListaSpesaDao listaSpesaDao = new ListaSpesaDaoImpl();
		
	    assertThrows(IllegalArgumentException.class, () -> {
	    	listaSpesaDao.findAllByUserId(null);
	    });
	    
	}
	
	
	

}
