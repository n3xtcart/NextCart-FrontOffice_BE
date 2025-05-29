package it.nextre.nextcart.controller;

import java.util.List;

import org.jboss.logging.Logger;

import it.nextre.aut.service.UserService;
import it.nextre.nextcart.dto.ListaSpesaDTO;
import it.nextre.nextcart.dto.ProdottoListaSpesaDTO;
import it.nextre.nextcart.service.ListaSpesaService;
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
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

@RolesAllowed("user")
@Path("/dashboard")
public class ListaSpesaController {
	
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
	
	public ListaSpesaController(Logger log) {
		this.log = log;
	}
	
		
	
	// ritorno tutte liste di un utente
    @GET
    @Path("/liste")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListeSpesaByUser(@Context SecurityContext securityContext) {	
    	String email = securityContext.getUserPrincipal() == null
                ? "anonimo"
                : securityContext.getUserPrincipal().getName();

            List<ListaSpesaDTO> liste = listaSpesaService.findByUserEmail(email);
            return Response.ok(liste).build();
    }
    
    // ritorno di una lista in particolare
    @GET
    @Path("liste/{listaId}")
    @Produces(MediaType.APPLICATION_JSON)

    public Response getListaSpesa(@PathParam("listaId") Long listaId) {
    	return null;
    }
    
	// creazione nuova lista 
    @POST
    @Path("/liste")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createListaSpesa(ListaSpesaDTO listaDTO, @Context SecurityContext securityContext) {
        String email = securityContext.getUserPrincipal() == null
            ? "anonimo"
            : securityContext.getUserPrincipal().getName();

        listaSpesaService.createLista(email, listaDTO);
        return Response.status(Response.Status.CREATED).build();
    }
    
    
    // eliminazione una lista in particolare
    @DELETE
    @Path("liste/{listaId}")
	@Produces(MediaType.APPLICATION_JSON)
    public Response deleteListaSpesa(@PathParam("id") Long id, @Context SecurityContext securityContext) {
        String email = securityContext.getUserPrincipal() == null
            ? "anonimo"
            : securityContext.getUserPrincipal().getName();

        listaSpesaService.deleteLista(email, id);
        return Response.noContent().build();
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
