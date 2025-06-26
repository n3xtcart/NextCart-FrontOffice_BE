package it.nextre.nextcart.test;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class ProvaService {

    @Inject
    @RestClient
    ProvaRestClient client;

    public String callExternalApi() {
        return client.getExternalMessage();
    }
}
