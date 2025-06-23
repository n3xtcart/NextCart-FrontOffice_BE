package it.nextre.nextcart.client;

import java.util.List;
import java.util.Optional;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import io.quarkus.arc.profile.IfBuildProfile;
import it.nextre.nextcart.dto.ProdottoDTO;
import it.nextre.nextcart.service.ServizioProdotto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
@IfBuildProfile("produzione")
public class ProdottoRestClientAdapter implements ServizioProdotto {
	
    @Inject
    @RestClient
    ProdottoRestClient prodottoRestClient;
    
    private Logger log; 
    
    public ProdottoRestClientAdapter(Logger log) {
    	this.log = log;
    }

	@Override
	public Optional<ProdottoDTO> trovaPerId(Long id) {
		log.infof("Richiesta al servizio esterno Prodotto: trovaPerId con id = %d avviata", id);
		return prodottoRestClient.trovaPerId(id);
	}

	@Override
	public List<ProdottoDTO> trovaTutti() {
		log.infof("Richiesta al servizio esterno Prodotto: trovaTutti avviata");
		return prodottoRestClient.trovaTutti();
	}

	@Override
	public List<ProdottoDTO> trovaPerIdCategoria(Long idCategoria) {
		log.infof("Richiesta al servizio esterno Prodotto: trovaPerIdCategoria con id = %d avviata", idCategoria);
		return prodottoRestClient.trovaPerIdCategoria(idCategoria);
	}

}
