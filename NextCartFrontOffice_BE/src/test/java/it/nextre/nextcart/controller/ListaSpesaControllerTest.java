package it.nextre.nextcart.controller;

import io.quarkus.test.junit.QuarkusTest;
import it.nextre.nextcart.dao.ListaSpesaRepository;
import it.nextre.nextcart.dto.ListaSpesaRequestDTO;
import it.nextre.nextcart.dto.ListaSpesaResponseDTO;
import it.nextre.nextcart.dto.UserListaSpesaDTO;
import it.nextre.nextcart.entity.ListaSpesa;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class ListaSpesaControllerTest {

    @Inject
    ListaSpesaController controller;

    @Inject
    ListaSpesaRepository repo;


    @Transactional
    @BeforeEach
    void setUp() {
        repo.deleteAll();

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
        dto.setDataPrevista(LocalDate.of(2025, 11, 1));

        Response response = controller.createLista(dto);

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        assertNotNull(response.getEntity());

        UserListaSpesaDTO result = (UserListaSpesaDTO) response.getEntity();
        assertFalse(result.getListeSpesa().isEmpty());
        assertEquals("Nuova lista test", result.getListeSpesa().get(0).getNomeLista());
    }

    @Test
    void getListeByUser() {

        Response response = controller.getListeByUser();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertNotNull(response.getEntity());

        UserListaSpesaDTO result = (UserListaSpesaDTO) response.getEntity();
        assertFalse(result.getListeSpesa().isEmpty());
        assertEquals("Spesa Test", result.getListeSpesa().get(0).getNomeLista());
    }

    @Test
    void getListaById() {

        ListaSpesa lista = repo.find("nome", "Spesa Test").firstResult();
        assertNotNull(lista);

        Response response = controller.getListaById(lista.id);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        ListaSpesaResponseDTO result = (ListaSpesaResponseDTO) response.getEntity();

        assertNotNull(result);
        assertEquals("Spesa Test", result.getNomeLista());
        assertEquals(LocalDate.of(2025, 10, 12), result.getDataPrevista());
    }

    @Test
    void deleteLista() {

        ListaSpesa lista = repo.find("nome", "Spesa Test").firstResult();
        assertNotNull(lista);

        Response response = controller.deleteLista(lista.id);
        assertEquals(204, response.getStatus());

        repo.getEntityManager().clear();

        ListaSpesa deleted = repo.findById(lista.id);
        assertNull(deleted);
    }
}