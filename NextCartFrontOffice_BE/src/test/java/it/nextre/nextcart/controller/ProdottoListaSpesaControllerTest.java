package it.nextre.nextcart.controller;

import io.quarkus.test.junit.QuarkusTest;
import it.nextre.nextcart.dao.ListaSpesaRepository;
import it.nextre.nextcart.dao.ProdottoListaSpesaRepository;
import it.nextre.nextcart.dto.ProdottoListaSpesaRequestDTO;
import it.nextre.nextcart.entity.ListaSpesa;
import it.nextre.nextcart.entity.ProdottoListaSpesa;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.inject.Inject;


import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class ProdottoListaSpesaControllerTest {

    @Inject
    ProdottoListaSpesaController controller;

    @Inject
    ProdottoListaSpesaRepository prodottoRepo;

    @Inject
    ListaSpesaRepository listaRepo;

    private ListaSpesa listaSpesa;
    private ProdottoListaSpesa prodotto;


    @Transactional
    @BeforeEach
    void setUp() {

        prodottoRepo.deleteAll();
        listaRepo.deleteAll();

        listaSpesa = new ListaSpesa();
        listaSpesa.setNome("Lista Test");
        listaSpesa.setIdUtente(23L);
        listaSpesa.setDataPrevista(LocalDate.now());
        listaRepo.persist(listaSpesa);

    }

    @Test
    void addProdotto() {

        ProdottoListaSpesaRequestDTO dto = new ProdottoListaSpesaRequestDTO();
        dto.setIdProdottoShop(1L);
        dto.setQuantitaProdotto(BigDecimal.valueOf(2.50));
        dto.setNoteProdotto("Note test");
        dto.setCheckedProdotto(Boolean.FALSE);

        Response response = controller.addProdotto(listaSpesa.id, dto);

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());

        long count = prodottoRepo.count();
        assertEquals(1, count);

        ProdottoListaSpesa saved = prodottoRepo.findAll().firstResult();
        assertNotNull(saved);
        assertEquals(dto.getIdProdottoShop(), saved.getIdProdottoShop());
        assertEquals(dto.getNoteProdotto(), saved.getNote());
        assertEquals(dto.getCheckedProdotto(), saved.getChecked());
        assertEquals(listaSpesa.id, saved.getListaSpesa().id);
    }

    @Transactional
    @Test
    void updateProdotto() {
        ProdottoListaSpesa prodotto = new ProdottoListaSpesa();
        prodotto.setIdProdottoShop(10L);
        prodotto.setQuantita(BigDecimal.valueOf(1.5));
        prodotto.setNote("Vecchia nota");
        prodotto.setChecked(false);
        prodotto.setListaSpesa(listaSpesa);
        prodottoRepo.persist(prodotto);
        prodottoRepo.getEntityManager().flush();

        Long prodottoId = prodotto.id;

        ProdottoListaSpesaRequestDTO dto = new ProdottoListaSpesaRequestDTO();
        dto.setIdProdottoShop(10L);
        dto.setQuantitaProdotto(BigDecimal.valueOf(3.0));
        dto.setNoteProdotto("Nota aggiornata");
        dto.setCheckedProdotto(Boolean.TRUE);

        Response response = controller.updateProdotto(prodottoId, dto);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        ProdottoListaSpesa updated = prodottoRepo.findById(prodottoId);
        assertNotNull(updated);
        assertEquals(dto.getIdProdottoShop(), updated.getIdProdottoShop());
        assertEquals(dto.getQuantitaProdotto(), updated.getQuantita());
        assertEquals(dto.getNoteProdotto(), updated.getNote());
        assertEquals(dto.getCheckedProdotto(), updated.getChecked());
}

    @Transactional
    @Test
    void deleteProdotto() {
        ProdottoListaSpesa prodotto = new ProdottoListaSpesa();
        prodotto.setIdProdottoShop(20L);
        prodotto.setQuantita(BigDecimal.valueOf(2.0));
        prodotto.setNote("Da eliminare");
        prodotto.setChecked(false);
        prodotto.setListaSpesa(listaSpesa);
        prodottoRepo.persist(prodotto);
        prodottoRepo.getEntityManager().flush();

        Long prodottoId = prodotto.id;

        Response response = controller.deleteProdotto(prodottoId);

        assertTrue(
                response.getStatus() == Response.Status.OK.getStatusCode() ||
                        response.getStatus() == Response.Status.NO_CONTENT.getStatusCode()
        );

        ProdottoListaSpesa deleted = prodottoRepo.findById(prodottoId);
        assertNull(deleted, "Il prodotto dovrebbe essere stato eliminato dal database");
    }
}