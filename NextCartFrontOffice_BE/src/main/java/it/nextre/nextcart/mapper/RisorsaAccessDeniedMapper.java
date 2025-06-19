package it.nextre.nextcart.mapper;

import org.jboss.logging.Logger;
import it.nextre.nextcart.exception.RisorsaAccessDeniedException;
import it.nextre.nextcart.exception.ErrorResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class RisorsaAccessDeniedMapper implements ExceptionMapper<RisorsaAccessDeniedException>{
	
	private Logger log;
	
	public RisorsaAccessDeniedMapper(Logger log){
		this.log = log;
	}
	
    @Override
    public Response toResponse(RisorsaAccessDeniedException exception) {
    	
    	log.errorf("Eccezione con stato 403: %s", exception.getMessage());
    	
        return Response.status(Response.Status.NOT_FOUND)
                       .entity(new ErrorResponse("NOT_FOUND", "Risorsa non trovata"))
                       .build();
    }

}
