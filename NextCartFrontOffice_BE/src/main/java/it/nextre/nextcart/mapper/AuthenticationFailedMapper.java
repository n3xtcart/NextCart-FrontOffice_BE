package it.nextre.nextcart.mapper;

import org.jboss.logging.Logger;
import io.quarkus.security.AuthenticationFailedException;
import it.nextre.nextcart.exception.ErrorResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class AuthenticationFailedMapper implements ExceptionMapper<AuthenticationFailedException>{
	
	private Logger log;
	
	public AuthenticationFailedMapper(Logger log){
		this.log = log;
	}

	@Override
	public Response toResponse(AuthenticationFailedException exception) {

		log.errorf("Eccezione 401: token con issuer non valido o scaduto");
    	
        return Response.status(Response.Status.UNAUTHORIZED)
                       .entity(new ErrorResponse("UNAUTHORIZED", "Autenticazione fallita" ))
                       .build();  
	}

}
