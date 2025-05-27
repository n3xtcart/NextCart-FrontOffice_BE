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
	

	@Test
	public void testFindAllByUserId() {
		
	}
	
	@Test
	public void testFindAllByNotUserId() {

	}
	
	@Test
	public void testFindAllByIdNull() {
	    
	}
	
	@Test
	@Transactional
    public void testSaveSuccess() {
				
    }
	
	@Test
	@Transactional
	public void testUpdateSucess() {

	}
	
	@Test
	public void testFindLista() {
		
	}

}
