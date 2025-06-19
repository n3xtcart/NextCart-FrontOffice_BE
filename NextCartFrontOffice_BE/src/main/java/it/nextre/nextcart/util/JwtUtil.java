package it.nextre.nextcart.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.security.AuthenticationFailedException;
import io.quarkus.security.identity.SecurityIdentity;
import io.smallrye.jwt.auth.principal.DefaultJWTCallerPrincipal;
import it.nextre.aut.dto.UserDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class JwtUtil {
	
    @Inject
    ObjectMapper objectMapper;
	
    public UserDTO estraiToken(SecurityIdentity securityIdentity) {
        try {
          
            DefaultJWTCallerPrincipal principal = (DefaultJWTCallerPrincipal) securityIdentity.getPrincipal();
            String userJson = principal.getClaim("user");
            
            if (userJson == null) {
                throw new AuthenticationFailedException("Claim 'user' mancante nel token");
            }
            
            UserDTO user = objectMapper.readValue(userJson, UserDTO.class);

            if (user.getId() == null) {
                throw new AuthenticationFailedException("Id utente mancante nel token");
            }

            return user;

        }catch (JsonProcessingException | ClassCastException | NullPointerException e ) {
            throw new AuthenticationFailedException("Errore durante la conversione in dto del token");
        }
    }

}
