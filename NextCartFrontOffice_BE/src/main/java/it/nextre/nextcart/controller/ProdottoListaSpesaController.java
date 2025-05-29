package it.nextre.nextcart.controller;


import org.jboss.logging.Logger;

import it.nextre.aut.service.UserService;
import it.nextre.nextcart.dto.ProdottoListaSpesaDTO;
import it.nextre.nextcart.service.ListaSpesaService;
import it.nextre.nextcart.service.ProdottoListaSpesaService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

@RolesAllowed("user")
@Path("/dashboardProdotti")
public class ProdottoListaSpesaController {
	
	@Inject
	private Logger log;
	
	@Inject
	ProdottoListaSpesaService prodottoListaSpesaService;
	
    @Inject
    UserService userService;
    
    @Inject
    ListaSpesaService listaSpesaService;
	 
	@Inject
	SecurityContext securityContext;
	
	public ProdottoListaSpesaController(Logger log) {
		this.log = log;
	}
        
    
    // aggiunta prodotto ad una lista in particolare
    @POST
    @Path("liste/{listaId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Response addProdottoToLista(@PathParam("listaId") Long listaId,ProdottoListaSpesaDTO prodottoDTO, @Context SecurityContext securityContext) {
		String email = securityContext.getUserPrincipal() == null
		? "anonimo"
		: securityContext.getUserPrincipal().getName();
		
		prodottoListaSpesaService.addProdottoToLista(listaId, email, prodottoDTO);
		return Response.status(Response.Status.CREATED).build();
		}
    
    
    // eliminazione prodotto da una lista in particolare
    @DELETE
    @Path("liste/{listaId}/prodotti/{prodottoId}")
	@Produces(MediaType.APPLICATION_JSON)
    public Response removeProdottoFromLista(@PathParam("listaId") Long listaId,  @PathParam("prodottoId") Long prodottoId, @Context SecurityContext securityContext) {
		String email = securityContext.getUserPrincipal() == null
		? "anonimo"
		: securityContext.getUserPrincipal().getName();
		
		prodottoListaSpesaService.removeProdotto(listaId, prodottoId, email);
		return Response.noContent().build();
		}
	
    
    // aggiornamento prodotto di una lista in particolare [flag]
    @PUT
    @Path("liste/{listaId}/prodotti/{prodottoId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Response updateProdotto(@PathParam("listaId") Long listaId, @PathParam("prodottoId") Long prodottoId, ProdottoListaSpesaDTO prodottoDTO, @Context SecurityContext securityContext) {
		String email = securityContext.getUserPrincipal() == null
		? "anonimo"
		: securityContext.getUserPrincipal().getName();
		
		prodottoListaSpesaService.updateProdotto(listaId, prodottoId, email, prodottoDTO);
		return Response.ok().build();
		}
    
    /* 
    @PUT
    @Path("liste/{listaId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Response updateListaSpesa(ListaSpesaDTO listaSpesaDTO) {
    	return null;
    }*/
}
