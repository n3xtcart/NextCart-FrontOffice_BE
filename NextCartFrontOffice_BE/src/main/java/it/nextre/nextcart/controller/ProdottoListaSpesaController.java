package it.nextre.nextcart.controller;

import org.jboss.logging.Logger;
import io.quarkus.security.identity.SecurityIdentity;
import it.nextre.nextcart.dto.ProdottoListaSpesaRequestDTO;
import it.nextre.nextcart.service.ProdottoListaSpesaService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

//@RolesAllowed("user")
@Path("/liste")
public class ProdottoListaSpesaController {
	
    private Logger log; 
    
    @Inject
    ProdottoListaSpesaService prodottoListaSpesaService;

    @Inject
    SecurityIdentity securityIdentity;
    
    public ProdottoListaSpesaController(Logger log) {
    	this.log = log;
    }
  
    @POST
    @Path("/{idLista}/prodotti")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProdotto (
    		@PathParam("idLista")  @Positive Long idLista,
    		@NotNull @Valid ProdottoListaSpesaRequestDTO prodottoDto) {
    	
    	log.infof("Richiesta POST: aggiunta di un nuovo prodotto alla lista con id: %d", idLista);
    	
    	Long idProdotto = prodottoListaSpesaService.addProdottoToLista(idLista, prodottoDto);
    	log.infof("Prodotto con id: %d aggiunto correttamente alla lista con id: %d", idProdotto, idLista);
    	return Response.status(Response.Status.CREATED).build();
    }
    
    @PUT
    @Path("/prodotti/{idProdotto}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProdotto(
    		@PathParam("idProdotto") @Positive Long idProdotto, 
            @NotNull @Valid ProdottoListaSpesaRequestDTO prodottoDto) {
    	
    	log.infof("Richiesta PUT: aggiornamento del prodotto con id: %d", idProdotto);
    	
    	var prodottoAggiornato = prodottoListaSpesaService.updateProdotto(idProdotto, prodottoDto);
    	log.infof("Prodotto con id: %d aggiornato con successo", idProdotto);
    	return Response.ok(prodottoAggiornato).build();
    }
    

    @DELETE
    @Path("/prodotti/{idProdotto}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProdotto (@PathParam("idProdotto") @Positive Long idProdotto) {
    	
        log.infof("Richiesta DELETE per eliminazione del prodotto con id: %d", idProdotto);
    	
    	Long idLista = prodottoListaSpesaService.removeProdotto(idProdotto);
    	log.infof("Eliminazione completata: prodotto con id %d rimosso dalla lista con id %d", idProdotto, idLista);
    	return Response.noContent().build();       
    }
    
}
