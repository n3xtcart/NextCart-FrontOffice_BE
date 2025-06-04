package it.nextre.nextcart.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jboss.logging.Logger;

import it.nextre.nextcart.dto.ListaSpesaDTO;
import it.nextre.nextcart.dto.ProdottoListaSpesaDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ListaSpesaServiceImpl implements ListaSpesaService {

    private Logger log; 

    private final Map<Long, String> userMap = new HashMap<>();

    private final ListaSpesaService listaSpesaService;

    

    @Override
    public ListaSpesaDTO createLista(String email, ListaSpesaDTO dto) {
        dto.setIdLista((long) (listeMockate.size() + 1));
        dto.setUserEmail(email);
        listeMockate.add(dto);
        log.infof("Creato nuova lista: {} per utente: {}", dto.getNomeLista(), email);
        return dto;
    }

    @Override
    public List<ListaSpesaDTO> getListeByUser(Long userId) {
        String email = userMap.get(userId);
        if (email == null) {
            log.warnf("Nessun utente trovato con userId: {}", userId);
            return Collections.emptyList();
        }
        List<ListaSpesaDTO> result = listeMockate.stream()
                .filter(l -> email.equals(l.getUserEmail()))
                .collect(Collectors.toList());
        log.infof("Trovate {} liste per utente con email: {}", result.size(), email);
        return result;
    }

    @Override
    public ListaSpesaDTO getListaByIdAndUser(Long listaId, Long userId) {
        String email = userMap.get(userId);
        if (email == null) {
            log.warnf("Nessun utente trovato con userId: {}", userId);
            return null;
        }
        ListaSpesaDTO lista = listeMockate.stream()
                .filter(l -> l.getIdLista().equals(listaId) && email.equals(l.getUserEmail()))
                .findFirst()
                .orElse(null);
        if (lista == null) {
            log.warnf("Lista non trovata con ID: {} per utente: {}", listaId, email);
        } else {
            log.infof("Lista trovata con ID: {} per utente: {}", listaId, email);
        }
        return lista;
    }

    @Override
    public boolean deleteLista(String email, Long listaId) {
        boolean removed = listeMockate.removeIf(l -> l.getIdLista().equals(listaId) && email.equals(l.getUserEmail()));
        if (removed) {
            log.infof("Lista con ID: {} eliminata per utente: {}", listaId, email);
        } else {
            log.warnf("Impossibile eliminare lista con ID: {} per utente: {}", listaId, email);
        }
        return removed;
    }

}