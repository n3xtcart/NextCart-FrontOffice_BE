package it.nextre.nextcart.controller;

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

    @Inject
    ProdottoListaSpesaService prodottoListaSpesaService;
    
    @Inject
    SecurityIdentity securityIdentity;
  
    @POST
    @Path("/{idLista}/prodotti")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProdotto (
    		@PathParam("idLista")  @Positive Long idLista,
    		@NotNull @Valid ProdottoListaSpesaRequestDTO prodottoDto) {
    	
    	//Long idUtente = Long.valueOf(securityIdentity.getAttribute("userId"));
    	Long idUtente = 23L; //TODO da eliminare
    	
    	prodottoListaSpesaService.addProdottoToLista(idUtente, idLista, prodottoDto);
    	return Response.status(Response.Status.CREATED).build();
    }
    
    @PUT
    @Path("/prodotti/{idProdotto}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProdotto(
    		@PathParam("idProdotto") @Positive Long idProdotto, 
            @NotNull @Valid ProdottoListaSpesaRequestDTO prodottoDto) {
    	
    	//Long idUtente = Long.valueOf(securityIdentity.getAttribute("userId"));
    	Long idUtente = 23L; //TODO da eliminare
    	
    	var prodottoAggiornato = prodottoListaSpesaService.updateProdotto(idUtente, idProdotto, prodottoDto);
    	return Response.ok(prodottoAggiornato).build();
    }
    

    @DELETE
    @Path("/prodotti/{idProdotto}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProdotto (@PathParam("idProdotto") @Positive Long idProdotto) {
    	
    	//Long idUtente = Long.valueOf(securityIdentity.getAttribute("userId"));
    	Long idUtente = 23L; //TODO da eliminare
    	
    	prodottoListaSpesaService.removeProdotto(idUtente, idProdotto);
    	return Response.noContent().build();       
    }
    
}
