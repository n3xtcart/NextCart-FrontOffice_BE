package it.nextre.nextcart.mapper;

import org.jboss.logging.Logger;
import io.quarkus.security.UnauthorizedException;
import it.nextre.nextcart.exception.ErrorResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UnauthorizedMapper implements ExceptionMapper<UnauthorizedException>{
	
	private Logger log;
	
	public UnauthorizedMapper(Logger log){
		this.log = log;
	}
	
	@Override
	public Response toResponse(UnauthorizedException exception) {
		
    	log.errorf("Eccezione 401: token mancante");
    	
        return Response.status(Response.Status.UNAUTHORIZED)
                       .entity(new ErrorResponse("UNAUTHORIZED", "Autenticazione richiesta" ))
                       .build();   
	}
	
}
