package it.nextre.nextcart.controller;

import java.util.Map;

import org.jboss.logging.Logger;
import io.quarkus.security.identity.SecurityIdentity;
import it.nextre.nextcart.dto.ProdottoListaSpesaRequestDTO;
import it.nextre.nextcart.service.ProdottoListaSpesaService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    @Inject
    Logger log;
        
    @Inject
    ProdottoListaSpesaService prodottoListaSpesaService;
    
    @Inject
    SecurityIdentity securityIdentity;

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
    public Response updateProdotto(@PathParam("idProdotto") @Positive Long idProdotto, @Valid ProdottoListaSpesaRequestDTO prodottoDto) {
    	
    	//Long idUtente = Long.valueOf(securityIdentity.getAttribute("userId"));
    	
    	Long idUtente = 23L; 
    	
    	prodottoListaSpesaService.updateProdotto(idProdotto, prodottoDto);
    	
    	return null;
    }
    

    @DELETE
    @Path("/{idProdotto}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProdotto (@PathParam("idProdotto") @Positive Long idProdotto) {
    	
    	//Long idUtente = Long.valueOf(securityIdentity.getAttribute("userId"));
    	
    	Long idUtente = 23L; 
    	
    	prodottoListaSpesaService.removeProdotto(idUtente, idProdotto);
    	return Response.noContent().build();       
    	}
    
	}  
