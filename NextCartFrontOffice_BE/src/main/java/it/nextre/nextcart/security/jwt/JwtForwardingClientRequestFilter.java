package it.nextre.nextcart.security.jwt;

import java.io.IOException;
import io.quarkus.security.credential.TokenCredential;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
@RequestScoped
public class JwtForwardingClientRequestFilter implements ClientRequestFilter{
	
    @Inject
    SecurityIdentity identity;

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        if (!identity.isAnonymous()) {
            TokenCredential tokenCred = identity.getCredential(TokenCredential.class);
            if (tokenCred != null) {
                String token = tokenCred.getToken();
                requestContext.getHeaders().add("Authorization", "Bearer " + token);
            }
        }
    }

}
