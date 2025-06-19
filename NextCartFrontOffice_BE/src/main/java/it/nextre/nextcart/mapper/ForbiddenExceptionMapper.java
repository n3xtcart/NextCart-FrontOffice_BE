package it.nextre.nextcart.mapper;

import org.jboss.logging.Logger;
import io.quarkus.security.ForbiddenException;
import it.nextre.nextcart.exception.ErrorResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;


@Provider
public class ForbiddenExceptionMapper implements ExceptionMapper<ForbiddenException>{
	
	private Logger log;
	
	public ForbiddenExceptionMapper(Logger log){
		this.log = log;
	}
	
    @Override
    public Response toResponse(ForbiddenException exception) {
    	
    	log.errorf("Eccezione con stato 403: utente senza permessi di accesso");
    	
        return Response.status(Response.Status.FORBIDDEN)
                       .entity(new ErrorResponse("FORBIDDEN", "Accesso negato"))
                       .build();
    }
	

}
