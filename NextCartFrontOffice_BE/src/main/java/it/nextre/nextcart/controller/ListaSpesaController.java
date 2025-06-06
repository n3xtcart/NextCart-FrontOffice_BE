package it.nextre.nextcart.controller;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;
import it.nextre.nextcart.dto.ListaSpesaRequestDTO;
import it.nextre.nextcart.dto.ListaSpesaResponseDTO;
import it.nextre.nextcart.dto.UserListaSpesaDTO;
import it.nextre.nextcart.service.ListaSpesaService;
import it.nextre.nextcart.service.ListaSpesaServiceImpl;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


//@RolesAllowed("user")
@Path("/liste-spesa")
public class ListaSpesaController {
    
    @Inject
    Logger log;

    @Inject
    ListaSpesaService listaSpesaService;

    @Inject
    JsonWebToken jwt;
    
    
    
    @POST
    @Path("/nuova-lista")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createLista(@Valid ListaSpesaRequestDTO dto) {
    	
        log.info("Richiesta POST per creare una nuova lista per l'utente con id: " + jwt.getClaim("id"));
        
        UserListaSpesaDTO created = listaSpesaService.createLista(dto);
        
        log.info("Lista creata con successo: " + created);
        
        return Response.status(Response.Status.CREATED).entity(created).build();
    }
    
    
    @GET
    @Path("/utente")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListeByUser() {
        //Long userId = jwt.getClaim("id");
        
        log.info("Richiesta GET per ottenere tutte le liste dell'utente con id: ");

        UserListaSpesaDTO response = listaSpesaService.getListeByUser();
        return Response.ok(response).build();
    }


    
    @GET
    @Path("/{id}")
    public Response getListaById(@PathParam("id") Long listaId) {
        //Long userId = jwt.getClaim("id");
        log.info("Richiesta GET per ottenere la lista con id: " + listaId);

        ListaSpesaResponseDTO response = listaSpesaService.getListaByIdAndUser(listaId);
        return Response.ok(response).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteLista(@PathParam("id") Long listaId) {
        //Long userId = jwt.getClaim("id");
        log.info("Richiesta DELETE per eliminare la lista con id: " + listaId);

        boolean deleted = listaSpesaService.deleteLista(listaId);

        if (deleted) {
            log.info("Lista eliminata con successo.");
            return Response.noContent().build(); // 204 No Content
        } else {
            log.warn("Lista non trovata o impossibile da eliminare.");
            return Response.status(Response.Status.NOT_FOUND).build(); // 404 Not Found
        }
    }
    
}