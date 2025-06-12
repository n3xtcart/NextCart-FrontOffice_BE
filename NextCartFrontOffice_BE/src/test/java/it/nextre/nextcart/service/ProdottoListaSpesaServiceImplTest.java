package it.nextre.nextcart.service;

import io.quarkus.test.junit.QuarkusTest;
import it.nextre.nextcart.dao.ListaSpesaRepository;
import it.nextre.nextcart.dao.ProdottoListaSpesaRepository;
import it.nextre.nextcart.dto.ProdottoListaSpesaRequestDTO;
import it.nextre.nextcart.dto.ProdottoListaSpesaResponseDTO;
import it.nextre.nextcart.entity.ListaSpesa;
import it.nextre.nextcart.entity.ProdottoListaSpesa;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.inject.Inject;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class ProdottoListaSpesaServiceImplTest {

    @Inject
    ProdottoListaSpesaServiceImpl service;

    @Inject
    ListaSpesaRepository listaRepository;

    @Inject
    ProdottoListaSpesaRepository prodottoRepository;

    private ListaSpesa listaTest;

    @Transactional
    @BeforeEach
    void setUp() {
        prodottoRepository.deleteAll();
        listaRepository.deleteAll();

        listaTest = new ListaSpesa();
        listaTest.setNome("Lista Test");
        listaTest.setIdUtente(23L);
        listaTest.setDataPrevista(LocalDate.of(2025, 10, 12));
        listaRepository.persist(listaTest);
    }

    @Test
    @Transactional
    void addProdottoToLista() {
        ProdottoListaSpesaRequestDTO dto = new ProdottoListaSpesaRequestDTO();
        dto.setIdProdottoShop(1L);
        dto.setQuantitaProdotto(new BigDecimal("1"));
        dto.setNoteProdotto("Note aggiunta");
        dto.setCheckedProdotto(false);

        service.addProdottoToLista(23L, listaTest.id, dto);

        var listaAggiornata = listaRepository.findById(listaTest.id);
        assertNotNull(listaAggiornata);

        var prodotti = listaAggiornata.getProdotti();
        assertFalse(prodotti.isEmpty());

        var prodotto = prodotti.stream()
                .filter(p -> p.getIdProdottoShop().equals(dto.getIdProdottoShop()))
                .findFirst()
                .orElse(null);

        assertNotNull(prodotto);
        assertEquals(dto.getQuantitaProdotto(), prodotto.getQuantita());
        assertEquals(dto.getNoteProdotto(), prodotto.getNote());
        assertEquals(dto.getCheckedProdotto(), prodotto.getChecked());
    }

    @Test
    @Transactional
    void updateProdotto() {
        ProdottoListaSpesa prodotto = new ProdottoListaSpesa();
        prodotto.setListaSpesa(listaTest);
        prodotto.setIdProdottoShop(1L);
        prodotto.setQuantita(new BigDecimal("1"));
        prodotto.setNote("Note originali");
        prodotto.setChecked(false);
        prodotto.setCreationTime(LocalDateTime.now());
        prodottoRepository.persist(prodotto);

        ProdottoListaSpesaRequestDTO dto = new ProdottoListaSpesaRequestDTO();
        dto.setIdProdottoShop(1L);
        dto.setQuantitaProdotto(new BigDecimal("5"));
        dto.setNoteProdotto("Note aggiornate");
        dto.setCheckedProdotto(true);

        ProdottoListaSpesaResponseDTO result = service.updateProdotto(23L, prodotto.id, dto);

        assertNotNull(result);
        assertEquals(new BigDecimal("5"), result.getQuantitaProdotto());
        assertEquals("Note aggiornate", result.getNoteProdotto());
        assertTrue(result.getCheckedProdotto());
    }

    @Test
    @Transactional
    void removeProdotto() {
        ProdottoListaSpesa prodotto = new ProdottoListaSpesa();
        prodotto.setListaSpesa(listaTest);
        prodotto.setIdProdottoShop(1L);
        prodotto.setQuantita(new BigDecimal("2"));
        prodotto.setNote("Da eliminare");
        prodotto.setChecked(false);
        prodottoRepository.persist(prodotto);

        service.removeProdotto(23L, prodotto.id);

        var prodottoEliminato = prodottoRepository.findById(prodotto.id);
        assertNull(prodottoEliminato);
    }
}