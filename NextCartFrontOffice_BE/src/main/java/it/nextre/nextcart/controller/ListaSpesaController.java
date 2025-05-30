package it.nextre.nextcart.controller;

import java.util.List;

import org.jboss.logging.Logger;

import it.nextre.aut.service.UserService;
import it.nextre.nextcart.dto.ListaSpesaDTO;
import it.nextre.nextcart.service.ListaSpesaService;
import it.nextre.nextcart.service.ProdottoListaSpesaService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

@RolesAllowed("user")
@Path("/liste-spesa")
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
    
    /*
     * Da aggiungere controllo token per autenticazione
     */
    
    // ritorno tutte le liste di un utente
    @GET
    @Path("/user/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListeByUser(@PathParam("userId") Long userId) {
        log.info("Richiesta GET per ottenere le liste dell'utente con ID: " + userId);
        List<ListaSpesaDTO> liste = listaSpesaService.getListeByUser(userId);
        log.info("Trovate " + liste.size() + " liste per l'utente con ID: " + userId);
        return Response.ok(liste).build();
    }

    // ritorno di una lista in particolare
    @GET
    @Path("/{listaId}/user/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListaByIdAndUser(@PathParam("listaId") Long listaId, @PathParam("userId") Long userId) {
        log.info("Richiesta GET per ottenere la lista con ID: " + listaId + " per l'utente con ID: " + userId);
        ListaSpesaDTO lista = listaSpesaService.getListaByIdAndUser(listaId, userId);
        if (lista == null) {
            log.warn("Lista non trovata con ID: " + listaId + " per l'utente con ID: " + userId);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        log.info("Lista trovata: " + lista);
        return Response.ok(lista).build();
    }

    // creazione nuova lista 
    @POST
    @Path("/nuova-lista")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createLista(@QueryParam("email") String email, ListaSpesaDTO dto) {
        log.info("Richiesta POST per creare una nuova lista per l'utente con email: " + email);
        ListaSpesaDTO created = listaSpesaService.createLista(email, dto);
        log.info("Lista creata con successo: " + created);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    // eliminazione una lista in particolare
    @DELETE
    @Path("/{listaId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteLista(@QueryParam("email") String email, @PathParam("listaId") Long listaId) {
        log.info("Richiesta DELETE per eliminare la lista con ID: " + listaId + " per l'utente con email: " + email);
        boolean deleted = listaSpesaService.deleteLista(email, listaId);
        if (deleted) {
            log.info("Lista con ID " + listaId + " eliminata con successo.");
            return Response.noContent().build();
        } else {
            log.warn("Lista con ID " + listaId + " non trovata o non eliminabile.");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}