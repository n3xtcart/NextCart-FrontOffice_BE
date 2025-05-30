package it.nextre.nextcart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.nextre.nextcart.dto.ProdottoListaSpesaDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProdottoListaSpesaServiceImpl implements ProdottoListaSpesaService{
	
    private final Map<Long, List<ProdottoListaSpesaDTO>> prodottiPerLista = new HashMap<>();
    private static final Logger logger = LogManager.getLogger(ProdottoListaSpesaServiceImpl.class);
    private final AtomicLong idGenerator = new AtomicLong(1);

	@Override
	public ProdottoListaSpesaDTO addProdottoToLista(Long listaId, String email, ProdottoListaSpesaDTO dto) {
        logger.info("Aggiunta del prodotto alla lista {} per l'utente {}", listaId, email);

        List<ProdottoListaSpesaDTO> lista = prodottiPerLista.computeIfAbsent(listaId, k -> new ArrayList<>());

        dto.setIdProdottoLista(idGenerator.getAndIncrement());
        lista.add(dto);

        logger.debug("Prodotto aggiunto: {}", dto);
        return dto;
	}

	@Override
	public void removeProdotto(Long listaId, Long prodottoId, String email) {
        logger.info("Rimozione del prodotto {} dalla lista {} per l'utente {}", prodottoId, listaId, email);

        List<ProdottoListaSpesaDTO> lista = prodottiPerLista.get(listaId);
        if (lista != null) {
            lista.removeIf(p -> Objects.equals(p.getIdProdottoLista(), prodottoId));
            logger.debug("Prodotto {} rimosso dalla lista {}", prodottoId, listaId);
        } else {
            logger.warn("Lista {} non trovata per la rimozione", listaId);
        }		
	}

	@Override
	public ProdottoListaSpesaDTO updateProdotto(Long listaId, Long prodottoId, String email, ProdottoListaSpesaDTO dto) {
        logger.info("Aggiornamento del prodotto {} nella lista {} per l'utente {}", prodottoId, listaId, email);

        List<ProdottoListaSpesaDTO> lista = prodottiPerLista.get(listaId);
        if (lista != null) {
            for (int i = 0; i < lista.size(); i++) {
                ProdottoListaSpesaDTO existing = lista.get(i);
                if (Objects.equals(existing.getIdProdottoLista(), prodottoId)) {
                    dto.setIdProdottoLista(prodottoId);
                    lista.set(i, dto);
                    logger.debug("Prodotto aggiornato: {}", dto);
                    return dto;
                }
            }
        }

        logger.warn("Prodotto {} non trovato nella lista {}", prodottoId, listaId);
        return null;
    }

}
