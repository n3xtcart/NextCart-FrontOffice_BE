package it.nextre.nextcart.util;

import org.eclipse.microprofile.jwt.JsonWebToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.nextre.aut.dto.UserDTO;
import it.nextre.nextcart.exception.TokenConvertException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class JwtUtil {
	
    @Inject
    JsonWebToken jwt;

    @Inject
    ObjectMapper objectMapper;
	
    public UserDTO estraiUtente() {
     
        var claim = jwt.getClaim("user");

        if (claim == null || claim.toString().isBlank()) {
            throw new TokenConvertException("Claim 'user' non presente o vuoto nel token");
        }

        try {    	
        	
            UserDTO dto = objectMapper.readValue(claim.toString(), UserDTO.class);

            if ( dto.getId() == null) {
                throw new TokenConvertException("Campo id nel claim 'user' mancante");
            }

            return dto;
            
        } catch (JsonProcessingException e) {  	
            throw new TokenConvertException("Errore nella deserializzazione del claim 'user'");
            
        }
    }
    
}
