package it.nextre.nextcart.mapper;

import org.jboss.logging.Logger;
import io.quarkus.security.AuthenticationFailedException;
import it.nextre.nextcart.exception.ErrorResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class AuthenticationMapper implements ExceptionMapper<AuthenticationFailedException>{
	
	private Logger log;
	
	public AuthenticationMapper(Logger log){
		this.log = log;
	}

	@Override
	public Response toResponse(AuthenticationFailedException exception) {

		String message = (exception.getMessage() != null && !exception.getMessage().isBlank())
			    ? exception.getMessage()
			    : "Issuer non valido";
		
		log.errorf("Eccezione 401: %s", message);
    	
        return Response.status(Response.Status.UNAUTHORIZED)
                       .entity(new ErrorResponse("UNAUTHORIZED", "Autenticazione fallita" ))
                       .build();  
	}

}
