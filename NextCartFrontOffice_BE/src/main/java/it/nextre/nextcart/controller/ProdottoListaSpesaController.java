package it.nextre.nextcart.controller;


import org.jboss.logging.Logger;

import jakarta.annotation.security.RolesAllowed;

import jakarta.ws.rs.Path;


@RolesAllowed("user")
@Path("/prodotti-lista")
public class ProdottoListaSpesaController {

    private Logger log;


    public ProdottoListaSpesaController(Logger log) {
        this.log = log;
    }
    
    /*
     * Da aggiungere controllo token per autenticazione
     */

    //  Aggiunta prodotto ad una lista spesa
    /*@POST
    @Path("liste/{listaId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProdottoToLista( @PathParam("listaId") Long listaId, @QueryParam("email") String email, ProdottoListaSpesaDTO dto) {
        
        log.infof("Richiesta aggiunta prodotto alla lista ID %d da parte dell'utente %s. Dati prodotto: %s", listaId, email, dto);
        ProdottoListaSpesaDTO result = prodottoListaSpesaService.addProdottoToLista(listaId, email, dto);
        log.infof("Prodotto aggiunto con successo alla lista ID %d. ID prodotto lista: %d", listaId, result.getIdProdottoLista());
        return Response.status(Response.Status.CREATED).entity(result).build();
    }

    //  Rimozione prodotto da una lista spesa
    @DELETE
    @Path("/{listaId}/prodotto/{prodottoId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeProdotto( @PathParam("listaId") Long listaId, @PathParam("prodottoId") Long prodottoId, @QueryParam("email") String email) {

        log.infof("Richiesta rimozione prodotto ID %d dalla lista ID %d da parte dell'utente %s", prodottoId, listaId, email);
        prodottoListaSpesaService.removeProdotto(listaId, prodottoId, email);
        log.infof("Prodotto ID %d rimosso con successo dalla lista ID %d", prodottoId, listaId);
        return Response.noContent().build();
    }

    //  Aggiornamento prodotto nella lista spesa (es. flag checked)
    @PUT
    @Path("/{listaId}/prodotto/{prodottoId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProdotto( @PathParam("listaId") Long listaId, @PathParam("prodottoId") Long prodottoId, @QueryParam("email") String email, ProdottoListaSpesaDTO dto) {

        log.infof("Richiesta aggiornamento prodotto ID %d nella lista ID %d da parte dell'utente %s. Nuovi dati: %s", prodottoId, listaId, email, dto);
        ProdottoListaSpesaDTO updated = prodottoListaSpesaService.updateProdotto(listaId, prodottoId, email, dto);
        log.infof("Prodotto ID %d aggiornato con successo nella lista ID %d", prodottoId, listaId);
        return Response.ok(updated).build();
    }*/

    
}
