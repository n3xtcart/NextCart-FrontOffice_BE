package it.nextre.nextcart.service;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import it.nextre.aut.dto.UserDTO;
import it.nextre.nextcart.dao.ListaSpesaRepository;
import it.nextre.nextcart.dto.ListaSpesaRequestDTO;
import it.nextre.nextcart.dto.ListaSpesaResponseDTO;
import it.nextre.nextcart.dto.UserListaSpesaDTO;
import it.nextre.nextcart.entity.ListaSpesa;
import it.nextre.nextcart.util.JwtUtil;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class ListaSpesaServiceImplTest {

    @Inject
    ListaSpesaServiceImpl service;

    @Inject
    ListaSpesaRepository repo;
    
    @InjectMock
    JwtUtil jwtUtil;

    @Transactional
    @BeforeEach
    void setUp() {
        repo.deleteAll();
        
        UserDTO mockUtente = new UserDTO();
        mockUtente.setId(23L);
        Mockito.when(jwtUtil.estraiUtente()).thenReturn(mockUtente);

        ListaSpesa lista = new ListaSpesa();
        lista.setNome("Spesa Test");
        lista.setIdUtente(23L);
        lista.setDataPrevista(LocalDate.of(2025, 10, 12));
        repo.persist(lista);
    }

    @Test
    void createLista() {

        ListaSpesaRequestDTO dto = new ListaSpesaRequestDTO();
        dto.setNomeLista("Nuova lista test");
        dto.setDataPrevista(LocalDate.of(2025, 10, 12));

        UserListaSpesaDTO result = service.createLista(dto);

        assertNotNull(result);
        assertEquals(1, result.getListeSpesa().size());
        assertEquals("Nuova lista test", result.getListeSpesa().get(0).getNomeLista());
    }

    @Test
    void getListeByUser() {

        UserListaSpesaDTO result = service.getListeByUser();

        assertNotNull(result);
        assertFalse(result.getListeSpesa().isEmpty());
        assertEquals("Spesa Test", result.getListeSpesa().get(0).getNomeLista());
    }

    @Test
    void getListaByIdAndUser() {

        ListaSpesa lista = repo.find("nome", "Spesa Test").firstResult();
        assertNotNull(lista);

        ListaSpesaResponseDTO result = service.getListaByIdAndUser(lista.id);

        assertNotNull(result);
        assertEquals("Spesa Test", result.getNomeLista());
        assertEquals(LocalDate.of(2025, 10, 12), result.getDataPrevista());

    }

    @Test
    void deleteLista() {
        ListaSpesa lista = repo.find("nome", "Spesa Test").firstResult();
        assertNotNull(lista);

        boolean result = service.deleteLista(lista.id);

        assertTrue(result);
    }
}