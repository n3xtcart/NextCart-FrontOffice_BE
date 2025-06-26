package it.nextre.nextcart.client;

import java.util.List;
import java.util.Optional;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import io.quarkus.arc.profile.IfBuildProfile;
import it.nextre.nextcart.dto.CategoriaDTO;
import it.nextre.nextcart.exception.EccezioneAccessoDati;
import it.nextre.nextcart.exception.EccezioneRisorsaNonTrovata;
import it.nextre.nextcart.service.ServizioCategoria;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
@IfBuildProfile("produzione")
public class CategoriaRestClientAdapter implements ServizioCategoria {
	
    @Inject
    @RestClient
    CategoriaRestClient categoriaRestClient;
    
    private Logger log; 
    
    public CategoriaRestClientAdapter(Logger log) {
    	this.log = log;
    }

	@Override
	public Optional<CategoriaDTO> trovaPerId(Long id) {
		log.infof("Richiesta al servizio esterno Categoria: trovaPerId con id = %d avviata", id);
		return Optional.ofNullable(categoriaRestClient.trovaPerId(id));
	}

	@Override
	public List<CategoriaDTO> trovaTutte() {
		log.infof("Richiesta al servizio esterno Categoria: trovaTutte avviata");
		return categoriaRestClient.trovaTutte();
	}

	@Override
	public CategoriaDTO salva(CategoriaDTO categoriaDTO) {
		return null;
	}

	@Override
	public Optional<CategoriaDTO> trovaPerNome(String nome) {
		return Optional.empty();
	}

	@Override
	public CategoriaDTO modifica(CategoriaDTO categoriaDTO) throws EccezioneRisorsaNonTrovata {
		return null;
	}

	@Override
	public void elimina(Long id) throws EccezioneRisorsaNonTrovata, EccezioneAccessoDati {	
	}

}
