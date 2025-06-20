package it.nextre.nextcart.service;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import it.nextre.aut.dto.UserDTO;
import it.nextre.nextcart.client.ClientProd;
import it.nextre.nextcart.dao.ListaSpesaRepository;
import it.nextre.nextcart.dao.ProdottoListaSpesaRepository;
import it.nextre.nextcart.dto.ProdottoListaSpesaRequestDTO;
import it.nextre.nextcart.dto.ProdottoListaSpesaResponseDTO;
import it.nextre.nextcart.entity.ListaSpesa;
import it.nextre.nextcart.entity.ProdottoListaSpesa;
import it.nextre.nextcart.util.JwtUtil;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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

    @Inject
    ClientProd prodottoClient;
    
    @InjectMock
    JwtUtil jwtUtil;

    private ListaSpesa listaTest;

    @Transactional
    @BeforeEach
    void setUp() {
        prodottoRepository.deleteAll();
        listaRepository.deleteAll();
        
        UserDTO mockUtente = new UserDTO();
        mockUtente.setId(23L);
        Mockito.when(jwtUtil.estraiUtente()).thenReturn(mockUtente);

        listaTest = new ListaSpesa();
        listaTest.setNome("Lista Test");
        listaTest.setIdUtente(23L);
        listaTest.setDataPrevista(LocalDate.of(2025, 10, 12));
        listaRepository.persist(listaTest);
    }

    @Test
    @Transactional
    void addProdottoToLista() {
        Long idProdotto = prodottoClient.trovaTutti().get(0).getId();

        var dto = new ProdottoListaSpesaRequestDTO();
        dto.setIdProdottoShop(idProdotto);
        dto.setQuantitaProdotto(BigDecimal.valueOf(1));
        dto.setNoteProdotto("Nota di test");
        dto.setCheckedProdotto(false);

        service.addProdottoToLista(listaTest.id, dto);

        var prodotti = prodottoRepository.listAll();
        assertEquals(1, prodotti.size());

        var prodottoSalvato = prodotti.get(0);
        assertEquals(idProdotto, prodottoSalvato.getIdProdottoShop());
        assertEquals(dto.getQuantitaProdotto(), prodottoSalvato.getQuantita());
        assertEquals(dto.getNoteProdotto(), prodottoSalvato.getNote());
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

        ProdottoListaSpesaResponseDTO result = service.updateProdotto(prodotto.id, dto);

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

        service.removeProdotto(prodotto.id);

        var prodottoEliminato = prodottoRepository.findById(prodotto.id);
        assertNull(prodottoEliminato);
    }
}