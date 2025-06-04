package it.nextre.nextcart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

import org.jboss.logging.Logger;

import it.nextre.nextcart.dto.ProdottoListaSpesaDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProdottoListaSpesaServiceImpl implements ProdottoListaSpesaService{
	
    private final Map<Long, List<ProdottoListaSpesaDTO>> prodottiPerLista = new HashMap<>();
    private Logger log; 
    private final AtomicLong idGenerator = new AtomicLong(1);
    
    public ProdottoListaSpesaServiceImpl(Logger log) {
    	this.log = log;
    }

	@Override
	public ProdottoListaSpesaDTO addProdottoToLista(Long listaId, String email, ProdottoListaSpesaDTO dto) {
		log.infof("Aggiunta del prodotto alla lista {} per l'utente {}", listaId, email);

        List<ProdottoListaSpesaDTO> lista = prodottiPerLista.computeIfAbsent(listaId, k -> new ArrayList<>());

        dto.setIdProdottoLista(idGenerator.getAndIncrement());
        lista.add(dto);

        log.debugf("Prodotto aggiunto: {}", dto);
        return dto;
	}

	@Override
	public void removeProdotto(Long listaId, Long prodottoId, String email) {
		log.infof("Rimozione del prodotto {} dalla lista {} per l'utente {}", prodottoId, listaId, email);

        List<ProdottoListaSpesaDTO> lista = prodottiPerLista.get(listaId);
        if (lista != null) {
            lista.removeIf(p -> Objects.equals(p.getIdProdottoLista(), prodottoId));
            log.debugf("Prodotto {} rimosso dalla lista {}", prodottoId, listaId);
        } else {
        	log.warnf("Lista {} non trovata per la rimozione", listaId);
        }		
	}

	@Override
	public ProdottoListaSpesaDTO updateProdotto(Long listaId, Long prodottoId, String email, ProdottoListaSpesaDTO dto) {
		log.infof("Aggiornamento del prodotto {} nella lista {} per l'utente {}", prodottoId, listaId, email);

        List<ProdottoListaSpesaDTO> lista = prodottiPerLista.get(listaId);
        if (lista != null) {
            for (int i = 0; i < lista.size(); i++) {
                ProdottoListaSpesaDTO existing = lista.get(i);
                if (Objects.equals(existing.getIdProdottoLista(), prodottoId)) {
                    dto.setIdProdottoLista(prodottoId);
                    lista.set(i, dto);
                    log.debugf("Prodotto aggiornato: {}", dto);
                    return dto;
                }
            }
        }

        log.warnf("Prodotto {} non trovato nella lista {}", prodottoId, listaId);
        return null;
    }

}
