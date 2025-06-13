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
    	
    	log.errorf("Eccezione con stato 403: %s", exception.getMessage());
    	
        return Response.status(Response.Status.NOT_FOUND)
                       .entity(new ErrorResponse("NOT_FOUND", "Risorsa non trovata"))
                       .build();
    }

}
