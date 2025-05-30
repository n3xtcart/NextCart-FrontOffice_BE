package it.nextre.nextcart.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.nextre.nextcart.dto.ListaSpesaDTO;
import it.nextre.nextcart.dto.ProdottoListaSpesaDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ListaSpesaServiceImpl implements ListaSpesaService {

    private static final Logger log = LogManager.getLogger(ListaSpesaServiceImpl.class);

    private final List<ListaSpesaDTO> listeMockate = new ArrayList<>();
    private final Map<Long, String> userMap = new HashMap<>();

    private final ListaSpesaService listaSpesaService;

    public ListaSpesaServiceImpl(ListaSpesaService listaSpesaService) {
        this.listaSpesaService = listaSpesaService;

        userMap.put(1L, "mario.rossi@example.com");

        ListaSpesaDTO lista1 = new ListaSpesaDTO();
        lista1.setIdLista(1L);
        lista1.setNomeLista("Spesa settimanale");
        lista1.setUserEmail("mario.rossi@example.com");

        ProdottoListaSpesaDTO p1 = new ProdottoListaSpesaDTO();
        p1.setIdProdottoLista(101L);
        p1.setIdProdottoShop(1L);
        p1.setNomeProdotto("Latte");
        p1.setCategoriaProdotto("Uova & Latticini");
        p1.setIdTipologia(1L);
        p1.setTipologiaProdotto("Confezione");
        p1.setQuantitaProdotto(new BigDecimal("2"));
        p1.setNoteProdotto("Parzialmente scremato");
        p1.setCheckedProdotto(false);

        lista1.setProdotti(List.of(p1));

        ListaSpesaDTO lista2 = new ListaSpesaDTO();
        lista2.setIdLista(2L);
        lista2.setNomeLista("Cena con amici");
        lista2.setUserEmail("mario.rossi@example.com");

        ProdottoListaSpesaDTO p2 = new ProdottoListaSpesaDTO();
        p2.setIdProdottoLista(102L);
        p2.setIdProdottoShop(2L);
        p2.setNomeProdotto("Bistecca");
        p2.setCategoriaProdotto("Carne");
        p2.setIdTipologia(2L);
        p2.setTipologiaProdotto("Grammi");
        p2.setQuantitaProdotto(new BigDecimal("500"));
        p2.setNoteProdotto("Scottona");
        p2.setCheckedProdotto(true);

        lista2.setProdotti(List.of(p2));

        listeMockate.add(lista1);
        listeMockate.add(lista2);

        log.info("Mock dati inizializzati con {} liste.", listeMockate.size());
    }

    @Override
    public ListaSpesaDTO createLista(String email, ListaSpesaDTO dto) {
        dto.setIdLista((long) (listeMockate.size() + 1));
        dto.setUserEmail(email);
        listeMockate.add(dto);
        log.info("Creato nuova lista: {} per utente: {}", dto.getNomeLista(), email);
        return dto;
    }

    @Override
    public List<ListaSpesaDTO> getListeByUser(Long userId) {
        String email = userMap.get(userId);
        if (email == null) {
            log.warn("Nessun utente trovato con userId: {}", userId);
            return Collections.emptyList();
        }
        List<ListaSpesaDTO> result = listeMockate.stream()
                .filter(l -> email.equals(l.getUserEmail()))
                .collect(Collectors.toList());
        log.info("Trovate {} liste per utente con email: {}", result.size(), email);
        return result;
    }

    @Override
    public ListaSpesaDTO getListaByIdAndUser(Long listaId, Long userId) {
        String email = userMap.get(userId);
        if (email == null) {
            log.warn("Nessun utente trovato con userId: {}", userId);
            return null;
        }
        ListaSpesaDTO lista = listeMockate.stream()
                .filter(l -> l.getIdLista().equals(listaId) && email.equals(l.getUserEmail()))
                .findFirst()
                .orElse(null);
        if (lista == null) {
            log.warn("Lista non trovata con ID: {} per utente: {}", listaId, email);
        } else {
            log.info("Lista trovata con ID: {} per utente: {}", listaId, email);
        }
        return lista;
    }

    @Override
    public boolean deleteLista(String email, Long listaId) {
        boolean removed = listeMockate.removeIf(l -> l.getIdLista().equals(listaId) && email.equals(l.getUserEmail()));
        if (removed) {
            log.info("Lista con ID: {} eliminata per utente: {}", listaId, email);
        } else {
            log.warn("Impossibile eliminare lista con ID: {} per utente: {}", listaId, email);
        }
        return removed;
    }

}