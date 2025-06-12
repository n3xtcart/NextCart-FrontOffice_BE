package it.nextre.nextcart.mapper;

import org.jboss.logging.Logger;

import it.nextre.nextcart.exception.AccessoNegatoException;
import it.nextre.nextcart.exception.ErrorResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class AccessoNegatoMapper implements ExceptionMapper<AccessoNegatoException>{
	
	private Logger log;
	
	public AccessoNegatoMapper(Logger log){
		this.log = log;
	}
	
    @Override
    public Response toResponse(AccessoNegatoException exception) {
    	
    	log.error("Eccezione con stato 404: " + exception.getMessage());
    	
        return Response.status(Response.Status.FORBIDDEN)
                       .entity(new ErrorResponse("FORBIDDEN", exception.getMessage()))
                       .build();
    }

}
