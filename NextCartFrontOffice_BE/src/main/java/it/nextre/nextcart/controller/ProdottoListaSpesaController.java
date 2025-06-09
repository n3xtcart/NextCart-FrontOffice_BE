package it.nextre.nextcart.controller;

import java.util.Map;

import org.jboss.logging.Logger;
import it.nextre.nextcart.dto.ProdottoListaSpesaRequestDTO;
import it.nextre.nextcart.service.ProdottoListaSpesaService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.BadRequestException;
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
@Path("/liste/{idLista}/prodotti")
public class ProdottoListaSpesaController {

    private Logger log;
    
    @Inject
    ProdottoListaSpesaService prodottoListaSpesaService;

    public ProdottoListaSpesaController(Logger log) {
        this.log = log;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProdotto (@PathParam("idLista")  @NotNull String idLista, @Valid ProdottoListaSpesaRequestDTO prodottoDto) {
    	
    	Long idListaParse = 0L;
    	
    	if (idLista == null || prodottoDto == null) {
    		throw new BadRequestException("Il corpo della richiesta non può essere nullo.");
        }
    	
    	try {
    		idListaParse = Long.parseLong(idLista);
    		
    		if (idListaParse < 1) {
    			throw new BadRequestException("ID Lista non valido.");
    		}	
    	} catch (NumberFormatException e) {
    		throw new BadRequestException("ID Lista non valido.");
    	}
    	
    	var prodottoAggiunto = prodottoListaSpesaService.addProdottoToLista(idListaParse, prodottoDto);
    	
    	if (!prodottoAggiunto) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Errore durante l'aggiunta del prodotto.")
                    .build();
        }
    	
        return Response.status(Response.Status.CREATED)
                .entity(Map.of("success", true, "message", "Prodotto aggiunto con successo"))
                .build();	
    }
    
    
    
    
    
    
    
    
    
    
    
    @PUT
    @Path("/{idProdotto}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response aggiornaProdotto(@PathParam("idLista") Long idLista, 
                                     @PathParam("idProdotto") Long idProdotto, 
                                     @Valid ProdottoListaSpesaRequestDTO prodottoDto) {
    	return null;
    }
    
    @DELETE
    @Path("/{idProdotto}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response rimuoviProdotto (@PathParam("idLista") Long idLista, @PathParam("idProdotto") Long idProdotto) {
    	return null;
    }

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
