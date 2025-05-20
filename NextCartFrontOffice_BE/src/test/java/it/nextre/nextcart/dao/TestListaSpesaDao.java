package it.nextre.nextcart.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;
import it.nextre.nextcart.entity.ListaSpesa;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@QuarkusTest
public class TestListaSpesaDao {
	
	@Inject
	ListaSpesaDaoImpl dao;

	
//------------------------------------------------------------
	@Test
	public void testFindAllByUserId() {
		
		ListaSpesaDao listaSpesaDao = new ListaSpesaDaoImpl();
		Long userId = 1L;
		List<ListaSpesa> risultato = listaSpesaDao.findByUserId(userId);
		
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
		
		List<ListaSpesa> risultato = listaSpesaDao.findByUserId(userId);
		assertNotNull(risultato);
		assertTrue(risultato.isEmpty());	
	}
	
	@Test
	public void testFindAllByIdNull() {
		ListaSpesaDao listaSpesaDao = new ListaSpesaDaoImpl();
		
	    assertThrows(IllegalArgumentException.class, () -> {
	    	listaSpesaDao.findByUserId(null);
	    });
	    
	}
	
//-------------------------------------------------------------------
	
	@Test
	@Transactional
    public void testSaveSuccess() {
				
		ListaSpesa nuovaLista = new ListaSpesa();
        nuovaLista.setNome("Spesa di test");
        nuovaLista.setDataPrevista(LocalDate.of(2025, 12, 12));
        nuovaLista.setIdUtente(1L);
        
        ListaSpesa listaSalvata = dao.save(nuovaLista);

        assertNotNull(listaSalvata);
        assertNotNull(listaSalvata.getId());
        assertEquals("Spesa di test", listaSalvata.getNome());
    }
	
	@Test
	@Transactional
	public void testUpdateSucess() {
		ListaSpesa lista = new ListaSpesa();
		lista.setId(1L);
		lista.setNome("Spesa di test modificata");
		lista.setDataPrevista(LocalDate.of(2025, 12, 12));
		lista.setIdUtente(1L);
		
		dao.update(lista);
		assertEquals("Spesa di test modificata", lista.getNome());
	}
	
	@Test
	public void testFindLista() {	//da completare
		List<ListaSpesa> result = dao.findByUserId(1L);
	}

}
