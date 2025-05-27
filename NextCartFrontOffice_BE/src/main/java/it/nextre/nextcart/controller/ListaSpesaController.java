package it.nextre.nextcart.controller;


import org.jboss.logging.Logger;
import io.quarkus.security.Authenticated;
import it.nextre.nextcart.dto.ListaSpesaDTO;
import it.nextre.nextcart.dto.ProdottoListaSpesaDTO;
import it.nextre.nextcart.service.ProdottoListaSpesaService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;


@Authenticated 
@Path("/dashboard")
public class ListaSpesaController {
	
	private Logger log;
	
	@Inject
	ProdottoListaSpesaService prodottoListaSpesaService;
	 
	@Inject
	SecurityContext securityContext;
	
	public ListaSpesaController(Logger log) {
		this.log = log;
	}
	
    @POST
    @Path("/liste")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("user")
    public Response createListaSpesa(ListaSpesaDTO listaSpesaDto) {
       return null;
    }
    
    @GET
    @Path("/liste")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("user")
    public Response getListeSpesaByUser() {	//L'id dell'utente lo si recupera dal context
    	return null;
    }
	

    @PUT
    @Path("liste/{listaId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("user")
    public Response updateListaSpesa(ListaSpesaDTO listaSpesaDTO) {
    	return null;
    }
		
    @DELETE
    @Path("liste/{listaId}")
	@Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("user")
    public Response deleteListaSpesa(@PathParam("listaId") Long listaId) {
    	return null;
    }

    @GET
    @Path("liste/{listaId}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("user")
    public Response getListaSpesa(@PathParam("listaId") Long listaId) {
    	return null;
    }
      
    @POST
    @Path("liste/{listaId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("user")
    public Response addProdottoToLista(@PathParam("listaId") Long listaId, ProdottoListaSpesaDTO prodottoDTO) {
    	return null;
    }
    
    @DELETE
    @Path("liste/{listaId}/prodotti/{prodottoId}")
	@Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("user")
    public Response removeProdottoFromLista(@PathParam("listaId") Long listaId, @PathParam("prodottoId") Long prodottoId) {
    	return null;
    }
	
    @PUT
    @Path("liste/{listaId}/prodotti/{listaId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("user")
    public Response updateProdotto(@PathParam("listaId") Long listaId, ProdottoListaSpesaDTO prodottoDTO ) {
    	return null;
    }
    
}
