package it.nextre.nextcart.client;

import java.util.List;
import java.util.Optional;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import it.nextre.nextcart.dto.ProdottoDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/v1/prodotti")
@Produces(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey="shop-api")
public interface ProdottoRestClient {

    @GET
    @Path("/{id}")
    Optional<ProdottoDTO> trovaPerId(@PathParam("id") Long id);

    @GET
    List<ProdottoDTO> trovaTutti();

    @GET
    @Path("/categoria/{idCategoria}")
    List<ProdottoDTO> trovaPerIdCategoria(@PathParam("idCategoria") Long idCategoria);
}
