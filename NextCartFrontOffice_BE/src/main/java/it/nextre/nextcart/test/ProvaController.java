package it.nextre.nextcart.test;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.inject.Inject;

@Path("/test")
public class ProvaController {

    @Inject
    ProvaService service;

    @GET
    public String forwardCall() {
        return service.callExternalApi();
    }
    
}