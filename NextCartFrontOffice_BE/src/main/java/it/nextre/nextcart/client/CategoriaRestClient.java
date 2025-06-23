package it.nextre.nextcart.client;

import java.util.List;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import it.nextre.nextcart.dto.CategoriaDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/v1/categorie")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "shop-api")
public interface CategoriaRestClient {

    @GET
    @Path("/{id}")
    CategoriaDTO trovaPerId(@PathParam("id") Long id);

    @GET
    List<CategoriaDTO> trovaTutte();  
}
